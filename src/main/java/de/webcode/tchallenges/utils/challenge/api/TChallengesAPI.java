package de.webcode.tchallenges.utils.challenge.api;

import de.webcode.tchallenges.TChallenges;
import de.webcode.tchallenges.utils.challenge.ChallengeManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class TChallengesAPI {


    public void addChallenge(TChallenge tChallenge){
        TChallenges instance = TChallenges.getInstance();

        ChallengeManager challengeManager = instance.getChallengeManager();
        challengeManager.add(tChallenge);

    }

    public void addCommand(TChallenge challenge, TChallengeCommand command){
        ChallengeManager challengeManager = TChallenges.getInstance().getChallengeManager();
        ArrayList<TChallengeCommand> commands = challengeManager.getChallengeCommands(challenge);

        if(!commands.contains(command)) commands.add(command);

        challengeManager.setChallengeCommands(challenge, commands);
    }

    public void registerListener(Listener listener, TChallenge challenge) {
        ChallengeManager challengeManager = TChallenges.getInstance().getChallengeManager();
        ArrayList<Listener> challengeListeners = challengeManager.getChallengeListeners(challenge);
        if (!challengeListeners.contains(listener)) challengeListeners.add(listener);

        challengeManager.setChallengeEventListener(challenge, challengeListeners);
    }

    public static TChallengesAPI getInstance(){
        return TChallenges.getInstance().gettChallengesAPI();
    }
}
