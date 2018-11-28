import java.io.*;
import java.io.IOException;

public class Statistik {
    private int numb_symbols = 0;
    private int numb_letters = 0;
    private int numb_words = 0;
    private int numb_unic_words = 0;
    private String content = "" ;
    private String unicwords = "";

    public void filetostr(String x) {
        content = "";
        try {
            FileInputStream inFile = new FileInputStream(x);
            byte[] str = new byte[inFile.available()];
            inFile.read(str);
            content = new String(str);
        }catch (IOException exc) {
        }
    }

    public int count_symbols(){
        numb_symbols = 0;
        numb_symbols =  content.length();
        return numb_symbols;
    }

    public int count_letters(){
        numb_letters = 0;
        numb_letters = (int)content.chars().filter(Character::isLetter).count();
        return numb_letters;
    }

    public int count_words(){
        numb_words = 0;
        if(content.length() != 0){
            numb_words++;
            for (int i = 0; i < content.length(); i++) {
                if(content.charAt(i) == ' '){
                    numb_words++;
                }
            }
        }
        return numb_words;
    }

    public int count_unic_words() {
        numb_unic_words = 0;
        unicwords ="";
        String processedInput  = "";
        String currentWord     = "";
        final char   token = '#';
        for (char c : content.toCharArray()) {
            if (c != ' ') {
                processedInput += c;
                currentWord    += c;
            } else {
                processedInput += token;
                String  existingWord      = "";
                int     occurences        = 0;
                for (char c1 : processedInput.toCharArray()) {
                    if (c1 != token) {
                        existingWord += c1;
                    } else {
                        if (existingWord.equals(currentWord)) {
                            occurences++;
                        }
                        existingWord = "";
                    }
                }
                if (occurences <= 1) {
                    unicwords = currentWord + " " + unicwords;
                    numb_unic_words++;
                }
                currentWord = "";
            }
        }
        return numb_unic_words;
    }

    public String GetUnicWords () {
        return unicwords;
    }

}