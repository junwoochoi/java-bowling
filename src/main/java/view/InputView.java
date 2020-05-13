package view;

import java.util.Objects;
import java.util.Scanner;

import static domain.Player.NAME_LENGTH;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        Objects.requireNonNull(scanner);
        this.scanner = scanner;
    }

    public String inputPlayerName() {
        printInputMessage();
        return scanner.nextLine();
    }

    private void printInputMessage() {
        System.out.println("플레이어 이름은(" + NAME_LENGTH + " english letters)?: ");
    }
}
