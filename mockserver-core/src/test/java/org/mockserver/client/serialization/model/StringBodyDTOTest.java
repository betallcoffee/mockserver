package org.mockserver.client.serialization.model;

import com.google.common.base.Charsets;
import org.junit.Test;
import org.mockserver.model.Body;
import org.mockserver.model.StringBody;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockserver.model.StringBody.exact;

/**
 * @author jamesdbloom
 */
public class StringBodyDTOTest {

    @Test
    public void shouldReturnValuesSetInConstructor() {
        // when
        StringBodyDTO stringBody = new StringBodyDTO(new StringBody("some_body"));

        // then
        assertThat(stringBody.getString(), is("some_body"));
        assertThat(stringBody.getType(), is(Body.Type.STRING));
        assertThat(stringBody.getCharset(), nullValue());
    }

    @Test
    public void shouldReturnValuesSetInConstructorWithCharset() {
        // when
        StringBodyDTO stringBody = new StringBodyDTO(new StringBody("some_body", Charsets.UTF_16));

        // then
        assertThat(stringBody.getString(), is("some_body"));
        assertThat(stringBody.getType(), is(Body.Type.STRING));
        assertThat(stringBody.getCharset(), is(Charsets.UTF_16));
    }

    @Test
    public void shouldBuildCorrectObject() {
        // when
        StringBody stringBody = new StringBodyDTO(new StringBody("some_body", Charsets.UTF_16)).buildObject();

        // then
        assertThat(stringBody.getValue(), is("some_body"));
        assertThat(stringBody.getType(), is(Body.Type.STRING));
        assertThat(stringBody.getCharset(), is(Charsets.UTF_16));
    }

    @Test
    public void shouldReturnCorrectObjectFromStaticBuilder() {
        assertThat(exact("some_body"), is(new StringBody("some_body")));
        assertThat(exact("some_body", Charsets.UTF_16), is(new StringBody("some_body", Charsets.UTF_16)));
    }

    @Test
    public void coverage() {
        new StringBodyDTO();
    }
}
