package com.jorgediaz.usecases;

import com.jorgediaz.data.interfaces.IRecipesRepository;
import com.jorgediaz.usecases.utils.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.TestObserver;

@RunWith(MockitoJUnitRunner.class)
public class UpdateRecipeTitleUseCaseTest {

    @Mock
    private IRecipesRepository recipesRepository;

    private UpdateRecipeTitleUseCase useCase;

    @Before
    public void setUp() {
        useCase = new UpdateRecipeTitleUseCase(recipesRepository);
    }

    @Test
    public void execute_whenUpdateRecipeTitleIsCalled_shouldReturnObservableOfNewTitle() {
        // Given
        int recipeId = 1;
        String newTitle = "New Title";
        String expectedResult = "Updated Title";
        Pair<Integer, String> input = new Pair<>(recipeId, newTitle);

        Mockito.when(recipesRepository.updateRecipeTitle(recipeId, newTitle)).thenReturn(Single.just(expectedResult));

        // When
        TestObserver<String> testObserver = useCase.execute(input).test();

        // Then
        testObserver.assertValue(expectedResult);
    }


    @Test
    public void execute_whenUpdateRecipeTitleReturnsError_shouldReturnErrorObservable() {
        // Given
        int recipeId = 1;
        String newTitle = "New Title";
        Exception exception = new Exception();
        Pair<Integer, String> input = new Pair<>(recipeId, newTitle);

        Mockito.when(recipesRepository.updateRecipeTitle(recipeId, newTitle)).thenReturn(Single.error(exception));

        // When
        TestObserver<String> testObserver = useCase.execute(input).test();

        // Then
        testObserver.assertError(exception);
    }
}