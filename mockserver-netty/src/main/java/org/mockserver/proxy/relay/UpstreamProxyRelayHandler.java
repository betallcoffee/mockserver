package org.mockserver.proxy.relay;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;

import static org.mockserver.proxy.error.Logging.shouldIgnoreException;

public class UpstreamProxyRelayHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final Logger logger;
    private volatile Channel downstreamChannel;

    public UpstreamProxyRelayHandler(Channel downstreamChannel, Logger logger) {
        super(false);
        this.downstreamChannel = downstreamChannel;
        this.logger = logger;
    }

    /**
     * Closes the specified channel after all queued write requests are flushed.
     */
    public static void closeOnFlush(Channel ch) {
        if (ch != null && ch.isActive()) {
            ch.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.read();
        ctx.write(Unpooled.EMPTY_BUFFER);
    }

    @Override
    public void channelRead0(final ChannelHandlerContext ctx, final FullHttpRequest request) {
        downstreamChannel.writeAndFlush(request).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    ctx.channel().read();
                } else {
                    if (isNotSocketClosedException(future.cause())) {
                        logger.error("Exception while returning response for request \"" + request.getMethod() + " " + request.getUri() + "\"", future.cause());
                    }
                    future.channel().close();
                }
            }
        });
    }

    private boolean isNotSocketClosedException(Throwable cause) {
        return !(cause instanceof ClosedChannelException || cause instanceof ClosedSelectorException);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        closeOnFlush(downstreamChannel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (!shouldIgnoreException(cause)) {
            logger.warn("Exception caught by upstream relay handler -> closing pipeline " + ctx.channel(), cause);
        }
        closeOnFlush(ctx.channel());
    }

}
