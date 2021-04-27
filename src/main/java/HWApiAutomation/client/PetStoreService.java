package HWApiAutomation.client;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PetStoreService {
    private static PetStore petStore;

    public static PetStore getPetStore() {
        if (petStore == null) {
            Retrofit r = new
                    Retrofit.Builder().
                    addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl("https://petstore.swagger.io/")
                    .build();
            petStore = r.create(PetStore.class);
        }
        return petStore;
    }
}
