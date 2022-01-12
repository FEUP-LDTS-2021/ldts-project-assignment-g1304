package asteroids.view.screens;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class InstructionScreen extends ScreenView{
    private static final Font font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
    public InstructionScreen(){
        setFont(font);
    }
    @Override
    public void draw() throws IOException {
        clear();

        int stringPos = (getSize().getRows()-24)/2 + 1;
        int initStringPos = stringPos;
        try {
            File myObj = new File(getClass().getClassLoader().getResource("instructionDraw.txt").toURI());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                getGraphics().setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                if(stringPos == initStringPos + 2 || stringPos == initStringPos + 12 || stringPos == initStringPos + 20){
                    String begin = data.substring(0,2);
                    String colorful = data.substring(2,data.length()-2);
                    String end = data.substring(data.length()-2);
                    getGraphics().putString(getTerminalPosition(stringPos, data.length()), begin);
                    getGraphics().setForegroundColor(TextColor.Factory.fromString("#FF0000"));
                    getGraphics().putString(new TerminalPosition((getSize().getColumns()/2-data.length()/2) + 2,stringPos), colorful);
                    getGraphics().setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                    getGraphics().putString(new TerminalPosition((getSize().getColumns()/2-data.length()/2) + 2 + colorful.length(),stringPos), end);
                    stringPos ++;
                    continue;
                }
                getGraphics().putString(getTerminalPosition(stringPos, data.length()), data);
                stringPos ++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        refresh();
    }
    public TerminalPosition getTerminalPosition(int row, int stringLen){
        return new TerminalPosition(getSize().getColumns()/2-stringLen/2,row);
    }
    @Override
    public TerminalSize getSize() {
        return new TerminalSize(41, 24);
    }
}
