package at.bestsolution.quarkus;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("provider")
    public String providerTest(@QueryParam("metaData") IMetaData metaData) {
        return metaData.getMetaInfo();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("multi-class")
    public String multiPartClass(@MultipartForm MPDataClass data) {
        return data.metaData.metaInfo;
    }

    public static class MPDataClass {
        @FormParam("document")
        @PartType(MediaType.APPLICATION_OCTET_STREAM)
        public InputStream document;

        @FormParam("metaData")
        public MetaData metaData;
    }


    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("multi-interface")
    public String multiPartInterface(@MultipartForm MPDataInterface data) {
        return data.metaData.getMetaInfo();
    }

    public static class MPDataInterface {
        @FormParam("document")
        @PartType(MediaType.APPLICATION_OCTET_STREAM)
        public InputStream document;

        @FormParam("metaData")
        public IMetaData metaData;
    }
}