public class Beach extends PropertySquare {

    public Beach(String name, int position, int initialValue) {
        super(name, position, initialValue);
        setType(SquareType.BEACH);
    }

    @Override
    public void calculatePropertyValue(Player player) {
        switch (player.totalNumberOfBeach()) {
            case 1:
                setPropertyValue(25000);
                break;
            case 2:
                setPropertyValue(75000);
                break;
            case 3:
                setPropertyValue(100000);
                break;
            case 4:
                setPropertyValue(200000);
                break;
            default:
                break;
        }
    }

    @Override
    public void calculateVisitCost() {
        setVisitCost(getPropertyValue());
    }

    @Override
    public int calculateSellValue() {
        return 200000;
    }

    @Override
    public String toString() {
        return "|Name: " + getName() + "|Beach buy cost: $" + getInitialValue() + "|";
    }

    @Override
    public void calculateVisitCost(boolean isWorldCup) {
        calculateVisitCost(); // Just delegate to existing method
    }

    // Implementing unimplemented method to avoid exceptions
    @Override
    public void calculateUpgradeCost() {
        // No upgrade cost for beach
        setUpgradeCost(0);
    }

    // Implementing unimplemented method to avoid exceptions
    @Override
    public void calculatePropertyValue() {
        // No general calculation for beach, it's player-dependent
    }
}
