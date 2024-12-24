import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordReplacer {
    private static MyMap<String,String> map; // store the graph

    public static void main(String args[]){
        if(args.length != 3){ // IF NUMBER OF ARGUMENTS IS NOT 3
            System.err.println("Usage: java WordReplacer <input text file> <word replacements file> <bst|rbt|hash>");
            System.exit(1);
        }

        String inputTextFile = args[0]; // input text file
        String wordReplacementsFile = args[1]; // word replacement instructions file
        String dataStructure = args[2]; // map object type

        // SELECT MAP DATA STRUCTURE
        if(dataStructure.equals("bst")){
            map = new BSTreeMap<>();
        }else if(dataStructure.equals("rbt")){
            map = new RBTreeMap<>();
        }else if(dataStructure.equals("hash")){
            map = new MyHashMap<>();
        }else{
            System.err.println("Error: Invalid data structure '" + dataStructure + "' received.");
            System.exit(1);
        }

        // TEST IF INPUT-FILE EXISTS
        try {
            FileReader fr = new FileReader(inputTextFile);
        } catch (FileNotFoundException e){
            System.err.println("Error: Cannot open file '" + inputTextFile + "' for input.");
            System.exit(1);
        }

        // TEST IF WORD-REPLACE-FILE EXISTS
        try {
            BufferedReader r = new BufferedReader(new FileReader(wordReplacementsFile)); // (citation: https://www.geeksforgeeks.org/difference-between-bufferedreader-and-filereader-in-java/)
            String line;
            line = r.readLine();
            while(line != null){

                // CONSTRUCT THE MAP, the map is the graph
                String[] splitLine = line.trim().split("\\s+"); // split the words of each line by spaces, this will result in "originalWord" "->" "replacementWord"
                if(splitLine.length != 3){
                    System.err.println("Word replacement instructions are incorrectly formatted");
                    System.exit(1);
                }
                String original = splitLine[0]; // the first word will be the original
                String replacement = splitLine[2]; // after the "->" will be the replacement word
                String ogoriginal = original;
                String ogreplacement = replacement; // in case a cycle occurs, we can output original command

                // look for cycles by checking if roots of "original" and "replacement" are equal
                while(map.get(original)!=null){
                    original = map.get(original);
                }
                while(map.get(replacement)!=null){
                    replacement = map.get(replacement);
                }
                if(original.equals(replacement)){ // if two nodes point to the same root, a cycle exists
                    System.err.println("Error: Cycle detected when trying to add replacement rule: " + ogoriginal + " -> " + ogreplacement);
                    System.exit(1);
                }

                map.put(original, replacement); // if theres no cycle, update instructions

                line = r.readLine();
             } 

        } catch (FileNotFoundException e){ // IN CASE file not found
            System.err.println("Error: Cannot open file '" + wordReplacementsFile + "' for input.");
            System.exit(1);
        } catch (IOException e) { // IN CASE readline() throws error
            System.err.println("Error: An I/O error occurred reading '" + wordReplacementsFile + "'.");
            System.exit(1);
        }
        
        // PARSE THRU INPUT-TEXT FILE
        try {
            BufferedReader r = new BufferedReader(new FileReader(inputTextFile));
            StringBuilder out = new StringBuilder();
            int currInt = r.read();
            char curr = (char) currInt; 

            while(currInt != -1){
                StringBuilder word = new StringBuilder();
                StringBuilder nonword = new StringBuilder();
                while(Character.isLetter(curr) && currInt!=-1){ // while current char is a letter, build the word
                    word.append(curr);
                    currInt = r.read();
                    curr = (char) currInt;
                    
                } while(!Character.isLetter(curr) && currInt!=-1){ // while current char isnt a letter, build the nonword space in between
                    nonword.append(curr);
                    currInt = r.read();
                    curr = (char) currInt;
                }

                String wordStr = word.toString();
                String nonwordStr = nonword.toString();

                if(map.get(wordStr)!=null){ // replace the word if instructions said so
                    String swap = map.get(wordStr);
                    while(map.get(swap) != null){ // traverse up to find the root, (citation: Dr.B's KruskalMST2 file)
                        swap = map.get(swap);
                    }
                    while(!wordStr.equals(swap)){ // compression -- make all nodes point to the root (citation: Dr.B's KruskalMST2 file)
                        String parent = map.get(wordStr);
                        map.put(wordStr, swap);
                        wordStr = parent;
                    }
                    out.append(swap);
                }else{
                    out.append(wordStr);
                }
                out.append(nonwordStr); // non-letter chars in between words
            }

            System.out.printf("%s\n", out.toString()); // PRINT OUT FINAL RESULT

        } catch (IOException e) { // IN CASE read() throws error
            System.err.println("Error: An I/O error occurred reading '" + inputTextFile + "'.");
            System.exit(1);
        } 
    }
}