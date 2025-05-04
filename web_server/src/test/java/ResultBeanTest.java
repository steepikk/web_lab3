import beans.ResultBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import entities.ResultEntity;
import services.InputService;
import services.ResultService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResultBeanTest {

    @Mock
    private ResultService resultService;

    @Mock
    private InputService inputService;

    @InjectMocks
    private ResultBean resultBean;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        resultBean.setResult(new ResultEntity());
    }

    @Test
    public void testCheckHitSuccess() {
        ResultEntity result = new ResultEntity();
        result.setX(1.0);
        result.setY(1.0);
        result.setR(2.0);
        resultBean.setResult(result);

        when(inputService.checkPoint(any(ResultEntity.class))).thenReturn(true);
        when(resultService.findAll()).thenReturn(Arrays.asList(result));

        String redirect = resultBean.checkHit();

        assertEquals("main?faces-redirect=true", redirect);
        verify(inputService).validateInput(1.0, 1.0, 2.0);
        verify(resultService).save(any(ResultEntity.class));
        verify(resultService).findAll();
        assertNotNull(resultBean.getResults());
        assertNull(resultBean.getResult().getX());
        assertNull(resultBean.getResult().getY());
        assertNull(resultBean.getResult().getR());
    }

    @Test
    public void testCheckHitValidationFailure() {
        ResultEntity result = new ResultEntity();
        result.setX(5.0);
        result.setY(1.0);
        result.setR(2.0);
        resultBean.setResult(result);

        doThrow(new IllegalArgumentException("Invalid X value")).when(inputService)
                .validateInput(5.0, 1.0, 2.0);

        String redirect = resultBean.checkHit();

        assertNull(redirect);
        verify(inputService).validateInput(5.0, 1.0, 2.0);
        verify(resultService, never()).save(any(ResultEntity.class));
    }

    @Test
    public void testLoadResults() {
        List<ResultEntity> mockResults = Arrays.asList(
                new ResultEntity(1L, 1.0, 1.0, 2.0, true),
                new ResultEntity(2L, -1.0, -1.0, 2.0, false)
        );
        when(resultService.findAll()).thenReturn(mockResults);

        resultBean.loadResults();
        List<ResultEntity> results = resultBean.getResults();

        assertEquals(2, results.size());
        verify(resultService).findAll();
    }

    @Test
    public void testGetResultsLoadsWhenNull() {
        List<ResultEntity> mockResults = Arrays.asList(new ResultEntity(1L, 1.0, 1.0, 2.0, true));
        when(resultService.findAll()).thenReturn(mockResults);

        List<ResultEntity> results = resultBean.getResults();

        assertEquals(1, results.size());
        verify(resultService).findAll();
    }

    @Test
    public void testReset() {
        ResultEntity result = new ResultEntity();
        result.setX(1.0);
        result.setY(1.0);
        result.setR(2.0);
        resultBean.setResult(result);

        resultBean.reset();

        assertNull(resultBean.getResult().getX());
        assertNull(resultBean.getResult().getY());
        assertNull(resultBean.getResult().getR());
    }

    @Test
    public void testClearResults() {
        List<ResultEntity> mockResults = Arrays.asList(new ResultEntity());
        when(resultService.findAll()).thenReturn(mockResults);

        resultBean.clearResults();
        List<ResultEntity> results = resultBean.getResults();

        verify(resultService).deleteAll();
        verify(resultService).findAll();
        assertEquals(1, results.size());
    }

    @Test
    public void testDeleteResult() {
        Long id = 1L;

        resultBean.deleteResult(id);

        verify(resultService).deleteById(id);
    }
}