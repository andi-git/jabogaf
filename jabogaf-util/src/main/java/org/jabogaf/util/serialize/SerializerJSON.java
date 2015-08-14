package org.jabogaf.util.serialize;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jabogaf.util.copy.CloneBean;
import org.jabogaf.util.copy.CopyProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@JSON
public class SerializerJSON implements Serializer{

    private Gson gson;

    @PostConstruct
    private void init() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return aClass == CopyProperties.class || aClass == Serializer.class;
                    }
                })
                .create();
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String serialize(Object object) {
        return gson.toJson(object);
    }

    @Override
    public <T> T deserialize(String string, Class<T> type) {
        return gson.fromJson(string, type);
    }
}
