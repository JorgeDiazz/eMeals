package com.jorgediaz.usecases;

import com.jorgediaz.data.interfaces.IRecipesRepository;
import com.jorgediaz.domain.Recipe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class GetRecipesUseCaseTest {

    @Mock
    private IRecipesRepository recipesRepository;

    private GetRecipesUseCase useCase;

    @Before
    public void setUp() {
        useCase = new GetRecipesUseCase(recipesRepository);
    }

    @Test
    public void execute_whenGetRecipesIsCalled_shouldReturnObservableOfRecipesList() {
        // Given
        List<Recipe> expectedRecipes = Arrays.asList(new Recipe(), new Recipe(), new Recipe());

        Mockito.when(recipesRepository.getRecipes()).thenReturn(Observable.just(expectedRecipes));

        // When
        TestObserver<List<Recipe>> testObserver = useCase.execute(null).test();

        // Then
        testObserver.assertValue(expectedRecipes);
    }

    @Test
    public void execute_whenGetRecipesReturnsError_shouldReturnErrorObservable() {
        // Given
        Exception exception = new Exception();
        Mockito.when(recipesRepository.getRecipes()).thenReturn(Observable.error(exception));

        // When
        TestObserver<List<Recipe>> testObserver = useCase.execute(null).test();

        // Then
        testObserver.assertError(exception);
    }
}