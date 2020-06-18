package domain.state;

public class Strike extends FinishState {

    private Strike() {
    }

    public static Strike newInstance() {
        return new Strike();
    }

}
