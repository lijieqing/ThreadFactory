package hua.lee.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class ForHonor01 {
    private final static HashMap<String, Music> dataMap = new HashMap<>();
    private final static HashMap<String, ArrayList<Music>> styleMap = new HashMap<>();
    private final static ArrayList<Music> fullMusic = new ArrayList<>();
    private static String lastMusicStyle = "";
    private static final String UNKOWN = "UnkownStyle";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] cmds;
        String cmd;
        while ((cmd=in.nextLine()) != null){
            System.out.println(cmd);
            if (cmd.equals("")){
                break;
            }
            if (cmd.startsWith("I")){
                cmds = cmd.split(" ");
                if (cmds.length == 3){
                    inputMusic(cmds[1],cmds[2]);
                }
            }
            if (cmd.startsWith("P")){
                cmds = cmd.split(" ");
                if (cmds.length == 2){
                    playMusic(cmds[1]);
                }
            }
            if (cmd.startsWith("B")){
                cmds = cmd.split(" ");
                if (cmds.length == 2){
                    breakMusic(cmds[1]);
                }
            }
        }
        fullMusic.sort(new Comparator<Music>() {
            @Override
            public int compare(Music o1, Music o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        for (Music music : fullMusic) {
            System.out.println(music.getName()+":"+music.getScore());
        }
        for (int i = 0; i < fullMusic.size(); i++) {
            System.out.println(fullMusic.get(i).getName() + " " + fullMusic.get(i).getStyle());
        }
    }

    private static void inputMusic(String name, String style) {
        if (name == null || name.length() > 128) {
            return;
        }
        if (dataMap.keySet().size() > 512) {
            return;
        }
        if (style == null) {
            style = UNKOWN;
        }
        if (style.length() > 16) {
            return;
        }

        Music music = new Music(name, style);
        dataMap.put(name, music);
        if (!styleMap.containsKey(style)) {
            styleMap.put(style,new ArrayList<>());
        }
        ArrayList<Music> musicList = styleMap.get(style);
        musicList.add(music);
        fullMusic.add(music);
    }

    private static void playMusic(String name) {
        Music music = dataMap.get(name);
        if (music == null) return;
        music.setScore(music.getScore() + 3);
        if (!lastMusicStyle.equals(UNKOWN) && lastMusicStyle.equals(music.getStyle())) {
            ArrayList<Music> list = styleMap.get(music.getStyle());
            for (Music m : list) {
                if (!m.getName().equals(name)) {
                    m.setScore(m.getScore() + 1);
                }
            }
        }
        lastMusicStyle = music.getStyle();
    }

    private static void breakMusic(String name) {
        Music music = dataMap.get(name);
        if (music == null) return;
        music.setScore(music.getScore() - 2);
        if (!lastMusicStyle.equals(UNKOWN) && lastMusicStyle.equals(music.getStyle())) {
            ArrayList<Music> list = styleMap.get(music.getStyle());
            for (Music m : list) {
                if (!m.getName().equals(name)) {
                    m.setScore(m.getScore() - 1);
                }
            }
        }
        lastMusicStyle = music.getStyle();
    }

}

class Music {
    private String name;
    private String style;
    private int score;

    public Music(String name, String style) {
        this.name = name;
        this.style = style;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
