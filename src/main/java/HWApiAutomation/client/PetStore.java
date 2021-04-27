package HWApiAutomation.client;

import HWApiAutomation.model.Pet;
import retrofit2.Call;
import retrofit2.http.*;

public interface PetStore {
    @POST("/v2/pet")
    public Call<Pet> addPet(@Body Pet pet);

    @GET("/v2/pet/{id}")
    public Call<Pet> getPetById(@Path("id") int id);

    @DELETE("/v2/pet/{petId}")
    public Call<DeleteResponse> deletePetById(@Path("petId") int id);

    @PUT("/pet")
    public Call<Pet> updatePet(@Body Pet pet);
}
