import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class WeightModel {
    private double lastWeight = 0.0;
    private Unit lastFromUnit = Unit.KILOGRAM;
    private Unit lastToUnit = Unit.POUND;
    private double conversionResult = 0.0;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public enum Unit {
        KILOGRAM("Килограмм", 1.0),
        POUND("Фунт", 0.453592),
        POLAR_BEAR("Белый медведь", 450.0),
        AFRICAN_ELEPHANT("Африканский слон", 6000.0),
        ZHIGULI("Жигули", 955.0),
        BEER_CAN("Банка пива", 0.5),
        CHICKEN("Цыпленок", 2.5),
        PAPER_SHEET("Лист бумаги", 0.005);

        private final String name;
        private final double weightInKg;

        Unit(String name, double weightInKg) {
            this.name = name;
            this.weightInKg = weightInKg;
        }
        @Override
        public String toString() { return name; }
        public double getWeightInKg() { return weightInKg; }
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void calculateAndSave(double weight, Unit from, Unit to) {
        this.lastWeight = weight;
        this.lastFromUnit = from;
        this.lastToUnit = to;
        double weightInKg = weight * from.getWeightInKg();
        double oldResult = this.conversionResult;
        this.conversionResult = weightInKg / to.getWeightInKg();
        support.firePropertyChange("conversionResult", oldResult, this.conversionResult);
    }

    public double getLastWeight() { return lastWeight; }
    public Unit getLastFromUnit() { return lastFromUnit; }
    public Unit getLastToUnit() { return lastToUnit; }
    public double getConversionResult() { return conversionResult; }
}