package compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Compiler {

    public static void main(String[] args) {

        String source = "fuente.txt";
        String result = "output.txt";
        try {
            callLexer(source, result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void callLexer(String source, String result) throws FileNotFoundException, IOException {
        BufferedReader sourceReader;
        BufferedWriter outputWriter;
        Path curRelativePath = Paths.get(source);
        String sourceUri = curRelativePath.toAbsolutePath().toString();
        curRelativePath = Paths.get(result);
        String outputUri = curRelativePath.toAbsolutePath().toString();

        try {
            sourceReader = new BufferedReader(new FileReader(sourceUri));
            String strSourceCurLine = sourceReader.readLine();
            FileWriter outFile = new FileWriter(outputUri, false);
            outputWriter = new BufferedWriter(outFile);

            while (strSourceCurLine != null) {
                if (!"".equals(strSourceCurLine)) {
                    JsonLexer jlx = new JsonLexer(strSourceCurLine);

                    while (true) {

                        try {
                            /*esta sentencia imprime por pantalla,
                              pero se debe comentar la sentencia de abajo*/
                            //System.out.println(jlx.nextToken().toString());
                            
                            /*esta sentencia imprime en el archivo output.txt
                              que se encuentra en la carpeta raiz del proyecto
                              pero se debe comentar la sentencia de arriba*/
                            outputWriter.write(jlx.nextToken().toString() + "\n");
                        } catch (Exception ex) {
                            break;
                        }
                    }
                }
                strSourceCurLine = sourceReader.readLine();
            }

            sourceReader.close();
            outputWriter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
