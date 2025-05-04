package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import entities.ResultEntity;
import services.InputService;
import services.ResultService;

import java.io.Serializable;
import java.util.List;

@Named("resultBean")
@RequestScoped
public class ResultBean implements Serializable {
    private static final Logger logger = LogManager.getLogger(ResultBean.class);

    @Inject
    private ResultService resultService;

    @Inject
    private InputService inputService;


    @Getter
    @Setter
    private ResultEntity result = new ResultEntity();

    private List<ResultEntity> results;

    public String checkHit() {
        try {
            inputService.validateInput(result.getX(), result.getY(), result.getR());

            result.setResult(inputService.checkPoint(result));

            resultService.save(result);

            loadResults();

            logger.info("Успех, точка успешно проверена!");

            reset();


        } catch (IllegalArgumentException e) {
            logger.error("Ошибка валидации:" + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Ошибка:" + e.getMessage());
            return null;
        }
        return "main?faces-redirect=true";
    }

    public void loadResults() {
        results = resultService.findAll();
    }

    public void reset() {
        result = new ResultEntity();
        result.setX(null);
        result.setY(null);
        result.setR(null);
    }

    public List<ResultEntity> getResults() {
        if (results == null) {
            loadResults();
        }
        return results;
    }

    public void clearResults() {
        resultService.deleteAll();
        results = null;
        loadResults();
    }

    public void deleteResult(Long id) {
        resultService.deleteById(id);
    }
}
