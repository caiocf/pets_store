package br.com.pets.pets_store.delegate;
import br.com.pets.pets_store.model.Pet;
import br.com.pets.pets_store.resource.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public class DefaultApiDelegateImpl implements PetApiDelegate {

    private final NativeWebRequest request;

    public DefaultApiDelegateImpl(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public CompletableFuture<ResponseEntity<Pet>> addPet(Pet pet) {
        return PetApiDelegate.super.addPet(pet);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> deletePet(Long petId, String apiKey) {
        return PetApiDelegate.super.deletePet(petId, apiKey);
    }

    @Override
    public CompletableFuture<ResponseEntity<List<Pet>>> findPetsByStatus(String status) {
        return PetApiDelegate.super.findPetsByStatus(status);
    }

    @Override
    public CompletableFuture<ResponseEntity<List<Pet>>> findPetsByTags(List<String> tags) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "[ { \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ], \"name\" : \"doggie\", \"id\" : 10, \"category\" : { \"name\" : \"Dogs\", \"id\" : 1 }, \"tags\" : [ { \"name\" : \"name\", \"id\" : 0 }, { \"name\" : \"name\", \"id\" : 0 } ], \"status\" : \"available\" }, { \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ], \"name\" : \"doggie\", \"id\" : 10, \"category\" : { \"name\" : \"Dogs\", \"id\" : 1 }, \"tags\" : [ { \"name\" : \"name\", \"id\" : 0 }, { \"name\" : \"name\", \"id\" : 0 } ], \"status\" : \"available\" } ]";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                        String exampleString = "<pet> <id>10</id> <name>doggie</name> <category> <id>1</id> <name>Dogs</name> </category> <photoUrls> <photoUrls>aeiou</photoUrls> </photoUrls> <tags> <tag> <id>123456789</id> <name>aeiou</name> </tag> </tags> <status>aeiou</status> </pet>";
                        ApiUtil.setExampleResponse(request, "application/xml", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.OK);
        }, Runnable::run);
    }

    @Override
    public CompletableFuture<ResponseEntity<Pet>> getPetById(Long petId) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "{ \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ], \"name\" : \"doggie\", \"id\" : 10, \"category\" : { \"name\" : \"Dogs\", \"id\" : 1 }, \"tags\" : [ { \"name\" : \"name\", \"id\" : 0 }, { \"name\" : \"name\", \"id\" : 0 } ], \"status\" : \"available\" }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                        String exampleString = "<pet> <id>10</id> <name>doggie</name> <category> <id>1</id> <name>Dogs</name> </category> <photoUrls> <photoUrls>aeiou</photoUrls> </photoUrls> <tags> <tag> <id>123456789</id> <name>aeiou</name> </tag> </tags> <status>aeiou</status> </pet>";
                        ApiUtil.setExampleResponse(request, "application/xml", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.OK);
        }, Runnable::run);
    }

    @Override
    public CompletableFuture<ResponseEntity<Pet>> updatePet(Pet pet) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "{ \"photoUrls\" : [ \"photoUrls\", \"photoUrls\" ], \"name\" : \"doggie\", \"id\" : 10, \"category\" : { \"name\" : \"Dogs\", \"id\" : 1 }, \"tags\" : [ { \"name\" : \"name\", \"id\" : 0 }, { \"name\" : \"name\", \"id\" : 0 } ], \"status\" : \"available\" }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                        String exampleString = "<pet> <id>10</id> <name>doggie</name> <photoUrls> <photoUrls>aeiou</photoUrls> </photoUrls> <tags> </tags> <status>aeiou</status> </pet>";
                        ApiUtil.setExampleResponse(request, "application/xml", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.OK);
        }, Runnable::run);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> updatePetWithForm(Long petId, String name, String status) {
        return PetApiDelegate.super.updatePetWithForm(petId, name, status);
    }
}
