package HWApiAutomation;

import HWApiAutomation.client.DeleteResponse;
import HWApiAutomation.client.PetStore;
import HWApiAutomation.client.PetStoreService;
import HWApiAutomation.model.Category;
import HWApiAutomation.model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;


public class NewTest {

    private static PetStore petStore;

    private static Stream<Arguments> param() {
        Pet pet1 = new Pet();
        pet1.setId(1);
        pet1.setStatus("available");
        pet1.setName("Kosh'ka");

        Pet pet2 = new Pet();
        pet2.setId(500);
        Category category = new Category();
        category.setName("Dog");
        pet2.setCategory(category);
        return Stream.of(pet1,pet2).map(Arguments::of);
    }

    @BeforeAll
    public static void setup() {
        petStore = PetStoreService.getPetStore();
    }

    @ParameterizedTest
    @MethodSource("param")
    public void PostQueryStatus200Test(Pet pet) throws IOException {

        petStore.deletePetById(pet.getId()).execute();
        petStore.addPet(pet).execute();
        Pet actualPet = petStore.getPetById(pet.getId()).execute().body();

        Assertions.assertEquals(pet, actualPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public void GetQueryStatus200Test(Pet pet) throws IOException {

        petStore.addPet(pet).execute();
        Pet actualPet = petStore.getPetById(pet.getId()).execute().body();

        Assertions.assertEquals(pet, actualPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public void GetQueryStatus400Test() throws IOException {
        Pet pet = new Pet();
        pet.setId(3);


        petStore.addPet(pet).execute();
        petStore.deletePetById(3).execute();
        Pet actualPet = petStore.getPetById(3).execute().body();

        Assertions.assertNull(actualPet);
    }

    @Test
    public void PutQueryStatus200Test() throws IOException {
        Pet pet = new Pet();
        pet.setId(15);
        pet.setStatus("available");
        petStore.addPet(pet).execute();
        pet.setStatus("pending");
        petStore.updatePet(pet).execute();
        Pet actualPet = petStore.getPetById(15).execute().body();

        Assertions.assertEquals(pet, actualPet);
    }

    @Test
    public void PutQueryStatus400Test() throws IOException {
        Pet pet = new Pet();
        pet.setId(6);
        pet.setStatus("available");
        petStore.addPet(pet).execute();
        petStore.deletePetById(6).execute();
        pet.setStatus("pending");
        petStore.updatePet(pet).execute();
        Pet actualPet = petStore.getPetById(6).execute().body();

        Assertions.assertNull(actualPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public void DeleteQueryStatus200Test(Pet pet) throws IOException {
        petStore.addPet(pet).execute();
        petStore.deletePetById(pet.getId()).execute();
        Pet actualPet = petStore.getPetById(pet.getId()).execute().body();

        Assertions.assertNull(actualPet);
    }

    @ParameterizedTest
    @MethodSource("param")
    public void DeleteQueryStatus400Test(Pet pet) throws IOException {
        petStore.addPet(pet).execute();
        petStore.deletePetById(pet.getId()).execute();
        DeleteResponse actualResponse = petStore.deletePetById(pet.getId()).execute().body();

        Assertions.assertNull(actualResponse);
    }
}