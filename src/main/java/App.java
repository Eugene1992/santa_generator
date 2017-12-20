import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        final String input = "C:\\Users\\yede0517\\BECHTLE\\santa_generator\\src\\main\\resources\\source";
        final String output = "C:\\Users\\yede0517\\BECHTLE\\santa_generator\\src\\main\\resources\\generated";
        final File[] playerFiles = readFilesFromFolder(input);
        final List<PlayerNode> playerNodes = getPlayerNodes(playerFiles);
        final List<PlayerNode> groupedPlayerNodes = getGroupedPlayerNodes(playerNodes);
        writePlayerFilesIntoFolderAndGeneratePairs(groupedPlayerNodes, output);
        printGroupInfo(groupedPlayerNodes);
    }

    public static File[] readFilesFromFolder(final String path) {
        return new File(path).listFiles();
    }

    public static List<PlayerNode> getPlayerNodes(final File[] files) {
        return Arrays.stream(files).map(file -> new PlayerNode(file.getName(), file, null)).collect(Collectors.toList());
    }

    public static List<PlayerNode> getGroupedPlayerNodes(final List<PlayerNode> players) {
        Collections.shuffle(players);
        for (int i = 0; i < players.size(); i++) {
            final PlayerNode currentPlayer = players.get(i);
            if (i != players.size() - 1) {
                int nextPlayerIndex = i + 1;
                currentPlayer.setGiveTo(players.get(nextPlayerIndex));
            } else {
                currentPlayer.setGiveTo(players.get(0));
            }
        }
        return players;
    }

    public static void writePlayerFilesIntoFolderAndGeneratePairs(final List<PlayerNode> players, final String path) {
        players.forEach(playerNode -> {
            try {
                byte[] bytes = FileUtils.readFileToByteArray(playerNode.getForm());
                Files.write(Paths.get(path + "/" + playerNode.getRndNumber() + ".txt"), bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void printGroupInfo(final List<PlayerNode> players) {
        for (PlayerNode player : players) {
            System.out.println(player.getName() + " prepare gift for " + player.getGiveTo().getRndNumber());
        }
    }
}
