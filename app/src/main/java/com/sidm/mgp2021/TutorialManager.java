package com.sidm.mgp2021;

public class TutorialManager {
    boolean isTutorial;
    public final static TutorialManager instance = new TutorialManager();

    private TutorialManager()
    {
    }

    public void Init(boolean isT)
    {
        isTutorial = isT;
    }
    public void setTutorial(boolean tutorial) {
        isTutorial = tutorial;
    }

    public boolean getTutorial() {
        return isTutorial;
    }
}
