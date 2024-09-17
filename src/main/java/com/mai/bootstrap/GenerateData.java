package com.mai.bootstrap;

import com.mai.data.AI;
import com.mai.enums.Difficulty;
import com.mai.service.AIService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class GenerateData {

    public static void createUserWithImage() {
        createUsersStream();
    }

    private static void createUsersStream() {
        String rootPath = "/com/mai/Images/usercrops/";

        // can't read resource folder when build jar easily apperently -__-
        List<String> files = getFileNames(rootPath);

        Random random = new Random();
        String colour = "#d14957";

        for (int i = 0; i < files.size(); i++) {
            String imagePath = rootPath + files.get(i);

            AI ai = new AI(
                    FilenameUtils.removeExtension(files.get(i)).trim(),
                    colour,
                    imagePath,
                    2,
                    2,
                    3
            );

            if (i == 0) {
                ai.setAiTypes(new HashSet<>(Set.of(Difficulty.JOURNALIST)));
            } else if (i == 1) {
                ai.setAiTypes(new HashSet<>(Set.of(Difficulty.EASY)));
            } else if (i == 2) {
                ai.setAiTypes(new HashSet<>(Set.of(Difficulty.NORMAL)));
            } else {
                ai.setAiTypes(new HashSet<>(Set.of(Difficulty.values()[random.nextInt(3)])));
            }

            AIService.aiList.add(ai);
        }

    }

    private static List<String> getFileNames(String rootPath) {
        List<String> files = new ArrayList<>();

        String filePath = rootPath + "images.txt";

        InputStream inputStream = GenerateData.class.getResourceAsStream(filePath);

        try {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            String[] images = result.split("\n");

            Collections.addAll(files, images);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return files;
    }

}

