package at.bestsolution.quarkus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class ParameterConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        System.err.println("TYPE: " + rawType);
        if (rawType == IMetaData.class) {
            return (ParamConverter)new ParamConverter<IMetaData>() {

                @Override
                public IMetaData fromString(String value) {
                    var rv = new MetaData();
                    rv.metaInfo = "barfoo";
                    return rv;
                }

                @Override
                public String toString(IMetaData value) {
                    return "{ }";
                }

            };
        }
        return null;
    }
    
}