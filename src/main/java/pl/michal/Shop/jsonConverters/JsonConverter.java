package pl.michal.Shop.jsonConverters;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class JsonConverter<T> {

    private final String filename;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public JsonConverter(String filename) {
        this.filename = filename;
    }

    // conversion from object to json
    public void toJson(final T element) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            if (element == null) {
                throw new NullPointerException("ELEMENT IS NULL");
            }
            gson.toJson(element, fileWriter);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("TO JSON - JSON FILENAME EXCEPTION");
        }
    }

    // conversion from json to object
    public Optional<T> fromJson() {
        try (FileReader fileReader = new FileReader(filename)) {

            return Optional.of(gson.fromJson(fileReader, type));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("FROM JSON - JSON FILENAME EXCEPTION");
        }
        return Optional.empty();
    }
}
