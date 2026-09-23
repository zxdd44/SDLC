public class WeightController {
    private final WeightModel model;

    public WeightController(WeightModel model) {
        this.model = model;
    }

    public void processInput(String weightStr, WeightModel.Unit from, WeightModel.Unit to) throws NumberFormatException {
        double weight = Double.parseDouble(weightStr.replace(",", "."));
        if (weight < 0) {
            throw new NumberFormatException("Вес не может быть отрицательным");
        }
        model.calculateAndSave(weight, from, to);
    }
}