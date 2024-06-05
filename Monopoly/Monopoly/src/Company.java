public class Company extends PropertySquare {

    private Dice dice;

    public Company(String name, int position, int initialValue) {
        super(name, position, initialValue);
        setType(SquareType.COMPANY);
        dice = new Dice(); // Create a Dice instance
    }

    @Override
    public void calculatePropertyValue(Player player) {
        switch (player.totalNumberOfCompanies()) {
            case 1:
                setPropertyValue(25000);
                break;
            case 2:
                setPropertyValue(100000);
                break;
            default:
                break;
        }
    }

    @Override
    public void calculateVisitCost() {
        int numCompaniesOwned = getOwner().totalNumberOfCompanies();
        int roll = dice.roll(); // Roll the die using the Dice instance
        if (numCompaniesOwned == 1) {
            setVisitCost(getPropertyValue() * roll);
        } else if (numCompaniesOwned == 2) {
            setVisitCost(getPropertyValue() * roll);
        }
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