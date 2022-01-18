package asteroids.view.screens;

import java.util.List;

public class InstructionScreen extends InformationScreen{
    private static final int PADDING_X = 2;
    private static final int PADDING_Y = 1;
    private static final String instructionPath = "\\src\\main\\resources\\instructionDraw.txt";

    public InstructionScreen(){
        super(List.of(3, 13, 21), instructionPath, PADDING_X, PADDING_Y);
    }
}
