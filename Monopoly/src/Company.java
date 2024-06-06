public class Company extends PropertySquare {
    public Company(String name, int position, int initialValue) {
        super(name, position, initialValue);
        setType(SquareType.COMPANY);
    }

    @Override
    public void calculatePropertyValue(Player player) {
        switch (player.totalNumberOfCompanies()) {
            case 1:
                setPropertyValue(15000);
                break;
            case 2:
                setPropertyValue(30000);
                break;
            default:
                break;
        }
    }

    @Override
    public void calculateVisitCost() {
        int randomMultiplier = (int) (Math.random() * 6) + 1; // Generate a random number between 1 and 6
        setVisitCost(getPropertyValue() * randomMultiplier);
    }

    @Override
    public int calculateSellValue() {
        return 200000;
    }

    @Override
    public String toString() {
        return "|Name: " + getName() + "|Company buy cost: $" + getInitialValue() + "|";
    }

    @Override
    public void calculateVisitCost(boolean isWorldCup) {
        calculateVisitCost(); // Just delegate to existing method
    }

    // Implementing unimplemented method to avoid exceptions
    @Override
    public void calculateUpgradeCost() {
        // No upgrade cost for company
        setUpgradeCost(0);
    }

    // Implementing unimplemented method to avoid exceptions
    @Override
    public void calculatePropertyValue() {
        // No general calculation for company, it's player-dependent
    }
}