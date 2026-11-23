import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class CsvRowReader {

    /**
     * Läser alla CSV-rader i EN mapp (inte rekursivt – bara filer direkt i mappen).
     */
    public static List<String[]> readRowsFromFolder(Path folder,
                                                    String separator,
                                                    boolean skipHeader) throws IOException {
        List<String[]> rows = new ArrayList<>();

        try (Stream<Path> files = Files.list(folder)) {
            List<Path> csvFiles = files
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(".csv"))
                    .sorted()
                    .collect(Collectors.toList());

            for (Path csv : csvFiles) {
                List<String> lines = Files.readAllLines(csv);
                for (int i = 0; i < lines.size(); i++) {
                    if (skipHeader && i == 0) continue;
                    String line = lines.get(i).trim();
                    if (line.isEmpty()) continue;
                    rows.add(line.split(separator, -1));
                }
            }
        }
        return rows;
    }

    /**
     * Läser CSV-rader från ett urval av undermappar under rootDir.
     * Endast de mappar vars namn finns i subFolderNames behandlas.
     */
    public static List<String[]> readRowsFromSubFolders(Path rootDir,
                                                        Collection<String> subFolderNames,
                                                        String separator,
                                                        boolean skipHeader) throws IOException {
        List<String[]> rows = new ArrayList<>();

        for (String name : subFolderNames) {
            Path folder = rootDir.resolve(name);
            if (!Files.isDirectory(folder)) {
                System.err.println("Hittar ingen mapp: " + folder);
                continue;
            }
            rows.addAll(readRowsFromFolder(folder, separator, skipHeader));
        }
        return rows;
    }
}