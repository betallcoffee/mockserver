package org.mockserver.client.serialization.java;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.mockserver.client.serialization.Base64Converter;
import org.mockserver.model.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author jamesdbloom
 */
public class HttpRequestToJavaSerializerTest {

    @Test
    public void shouldSerializeFullObjectAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        request()" + System.getProperty("line.separator") +
                        "                .withMethod(\"GET\")" + System.getProperty("line.separator") +
                        "                .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                .withHeaders(" + System.getProperty("line.separator") +
                        "                        new Header(\"requestHeaderNameOne\", \"requestHeaderValueOneOne\", \"requestHeaderValueOneTwo\")," + System.getProperty("line.separator") +
                        "                        new Header(\"requestHeaderNameTwo\", \"requestHeaderValueTwo\")" + System.getProperty("line.separator") +
                        "                )" + System.getProperty("line.separator") +
                        "                .withCookies(" + System.getProperty("line.separator") +
                        "                        new Cookie(\"requestCookieNameOne\", \"requestCookieValueOne\")," + System.getProperty("line.separator") +
                        "                        new Cookie(\"requestCookieNameTwo\", \"requestCookieValueTwo\")" + System.getProperty("line.separator") +
                        "                )" + System.getProperty("line.separator") +
                        "                .withQueryStringParameters(" + System.getProperty("line.separator") +
                        "                        new Parameter(\"requestQueryStringParameterNameOne\", \"requestQueryStringParameterValueOneOne\", \"requestQueryStringParameterValueOneTwo\")," + System.getProperty("line.separator") +
                        "                        new Parameter(\"requestQueryStringParameterNameTwo\", \"requestQueryStringParameterValueTwo\")" + System.getProperty("line.separator") +
                        "                )" + System.getProperty("line.separator") +
                        "                .withSecure(true)" + System.getProperty("line.separator") +
                        "                .withKeepAlive(false)" + System.getProperty("line.separator") +
                        "                .withBody(new StringBody(\"responseBody\"))",
                new HttpRequestToJavaSerializer().serializeAsJava(1,
                        new HttpRequest()
                                .withMethod("GET")
                                .withPath("somePath")
                                .withQueryStringParameters(
                                        new Parameter("requestQueryStringParameterNameOne", "requestQueryStringParameterValueOneOne", "requestQueryStringParameterValueOneTwo"),
                                        new Parameter("requestQueryStringParameterNameTwo", "requestQueryStringParameterValueTwo")
                                )
                                .withHeaders(
                                        new Header("requestHeaderNameOne", "requestHeaderValueOneOne", "requestHeaderValueOneTwo"),
                                        new Header("requestHeaderNameTwo", "requestHeaderValueTwo")
                                )
                                .withCookies(
                                        new Cookie("requestCookieNameOne", "requestCookieValueOne"),
                                        new Cookie("requestCookieNameTwo", "requestCookieValueTwo")
                                )
                                .withSecure(true)
                                .withKeepAlive(false)
                                .withBody(new StringBody("responseBody"))
                )
        );
    }

    @Test
    public void shouldSerializeFullObjectWithParameterBodyRequestAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        request()" + System.getProperty("line.separator") +
                        "                .withBody(" + System.getProperty("line.separator") +
                        "                        new ParameterBody(" + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestBodyParameterNameOne\", \"requestBodyParameterValueOneOne\", \"requestBodyParameterValueOneTwo\")," + System.getProperty("line.separator") +
                        "                                new Parameter(\"requestBodyParameterNameTwo\", \"requestBodyParameterValueTwo\")" + System.getProperty("line.separator") +
                        "                        )" + System.getProperty("line.separator") +
                        "                )",
                new HttpRequestToJavaSerializer().serializeAsJava(1,
                        new HttpRequest()
                                .withBody(
                                        new ParameterBody(
                                                new Parameter("requestBodyParameterNameOne", "requestBodyParameterValueOneOne", "requestBodyParameterValueOneTwo"),
                                                new Parameter("requestBodyParameterNameTwo", "requestBodyParameterValueTwo")
                                        )
                                )
                )
        );
    }

    @Test
    public void shouldSerializeFullObjectWithBinaryBodyRequestAsJava() throws IOException {
        // when
        assertEquals(System.getProperty("line.separator") +
                        "        request()" + System.getProperty("line.separator") +
                        "                .withBody(Base64Converter.base64StringToBytes(\"" + Base64Converter.bytesToBase64String("responseBody".getBytes()) + "\"))",
                new HttpRequestToJavaSerializer().serializeAsJava(1,
                        new HttpRequest()
                                .withBody(
                                        new BinaryBody("responseBody".getBytes())
                                )
                ));
    }

    @Test
    public void shouldEscapeJSONBodies() throws IOException {
        assertEquals("" + System.getProperty("line.separator") +
                        "        request()" + System.getProperty("line.separator") +
                        "                .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                .withBody(new JsonBody(\"[" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"1\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"Xenophon's imperial fiction : on the education of Cyrus\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"James Tatum\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"0691067570\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"1989\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"2\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"You are here : personal geographies and other maps of the imagination\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"Katharine A. Harmon\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"1568984308\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"2004\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    {" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"id\\\": \\\"3\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"title\\\": \\\"You just don't understand : women and men in conversation\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"author\\\": \\\"Deborah Tannen\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"isbn\\\": \\\"0345372050\\\"," + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "        \\\"publicationDate\\\": \\\"1990\\\"" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "    }" + StringEscapeUtils.escapeJava(System.getProperty("line.separator")) + "]\", JsonBodyMatchType.ONLY_MATCHING_FIELDS))",
                new HttpRequestToJavaSerializer().serializeAsJava(1,
                        new HttpRequest()
                                .withPath("somePath")
                                .withBody(new JsonBody("[" + System.getProperty("line.separator") +
                                        "    {" + System.getProperty("line.separator") +
                                        "        \"id\": \"1\"," + System.getProperty("line.separator") +
                                        "        \"title\": \"Xenophon's imperial fiction : on the education of Cyrus\"," + System.getProperty("line.separator") +
                                        "        \"author\": \"James Tatum\"," + System.getProperty("line.separator") +
                                        "        \"isbn\": \"0691067570\"," + System.getProperty("line.separator") +
                                        "        \"publicationDate\": \"1989\"" + System.getProperty("line.separator") +
                                        "    }," + System.getProperty("line.separator") +
                                        "    {" + System.getProperty("line.separator") +
                                        "        \"id\": \"2\"," + System.getProperty("line.separator") +
                                        "        \"title\": \"You are here : personal geographies and other maps of the imagination\"," + System.getProperty("line.separator") +
                                        "        \"author\": \"Katharine A. Harmon\"," + System.getProperty("line.separator") +
                                        "        \"isbn\": \"1568984308\"," + System.getProperty("line.separator") +
                                        "        \"publicationDate\": \"2004\"" + System.getProperty("line.separator") +
                                        "    }," + System.getProperty("line.separator") +
                                        "    {" + System.getProperty("line.separator") +
                                        "        \"id\": \"3\"," + System.getProperty("line.separator") +
                                        "        \"title\": \"You just don't understand : women and men in conversation\"," + System.getProperty("line.separator") +
                                        "        \"author\": \"Deborah Tannen\"," + System.getProperty("line.separator") +
                                        "        \"isbn\": \"0345372050\"," + System.getProperty("line.separator") +
                                        "        \"publicationDate\": \"1990\"" + System.getProperty("line.separator") +
                                        "    }" + System.getProperty("line.separator") +
                                        "]"))
                )
        );
    }


    @Test
    public void shouldEscapeJsonSchemaBodies() throws IOException {
        String jsonSchema = "{" + System.getProperty("line.separator") +
                "    \"$schema\": \"http://json-schema.org/draft-04/schema#\"," + System.getProperty("line.separator") +
                "    \"title\": \"Product\"," + System.getProperty("line.separator") +
                "    \"description\": \"A product from Acme's catalog\"," + System.getProperty("line.separator") +
                "    \"type\": \"object\"," + System.getProperty("line.separator") +
                "    \"properties\": {" + System.getProperty("line.separator") +
                "        \"id\": {" + System.getProperty("line.separator") +
                "                  \"description\": \"The unique identifier for a product\"," + System.getProperty("line.separator") +
                "                  \"type\": \"integer\"" + System.getProperty("line.separator") +
                "        }," + System.getProperty("line.separator") +
                "        \"name\": {" + System.getProperty("line.separator") +
                "                  \"description\": \"Name of the product\"," + System.getProperty("line.separator") +
                "                  \"type\": \"string\"" + System.getProperty("line.separator") +
                "        }," + System.getProperty("line.separator") +
                "        \"price\": {" + System.getProperty("line.separator") +
                "                  \"type\": \"number\"," + System.getProperty("line.separator") +
                "                  \"minimum\": 0," + System.getProperty("line.separator") +
                "                  \"exclusiveMinimum\": true" + System.getProperty("line.separator") +
                "        }," + System.getProperty("line.separator") +
                "        \"tags\": {" + System.getProperty("line.separator") +
                "                  \"type\": \"array\"," + System.getProperty("line.separator") +
                "                  \"items\": {" + System.getProperty("line.separator") +
                "                      \"type\": \"string\"" + System.getProperty("line.separator") +
                "                  }," + System.getProperty("line.separator") +
                "                  \"minItems\": 1," + System.getProperty("line.separator") +
                "                  \"uniqueItems\": true" + System.getProperty("line.separator") +
                "        }" + System.getProperty("line.separator") +
                "    }," + System.getProperty("line.separator") +
                "    \"required\": [\"id\", \"name\", \"price\"]" + System.getProperty("line.separator") +
                "}";
        assertEquals("" + System.getProperty("line.separator") +
                        "        request()" + System.getProperty("line.separator") +
                        "                .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                .withBody(new JsonSchemaBody(\"" + StringEscapeUtils.escapeJava(jsonSchema) + "\"))",
                new HttpRequestToJavaSerializer().serializeAsJava(1,
                        new HttpRequest()
                                .withPath("somePath")
                                .withBody(new JsonSchemaBody(jsonSchema))
                )
        );
    }

    @Test
    public void shouldSerializeMinimalObjectAsJava() throws IOException {
        assertEquals(System.getProperty("line.separator") +
                        "        request()" + System.getProperty("line.separator") +
                        "                .withPath(\"somePath\")" + System.getProperty("line.separator") +
                        "                .withBody(new StringBody(\"responseBody\"))",
                new HttpRequestToJavaSerializer().serializeAsJava(1,
                        new HttpRequest()
                                .withPath("somePath")
                                .withBody("responseBody")
                )
        );
    }
}
