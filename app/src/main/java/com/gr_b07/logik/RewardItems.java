package com.gr_b07.logik;

import com.gr_b07.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
 * Icons made by <a href="https://www.flaticon.com/authors/photo3idea-studio" title="photo3idea_studio">photo3idea_studio</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
 * Icons made by <a href="https://www.flaticon.com/authors/prettycons" title="prettycons">prettycons</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
 */

public class RewardItems {

    private Reward fiveExperience, tenExperience, canteenTenKR, smoothie, juice, rawBar, proteinShake,
            iPhoneHolder, drinkBottle, trainingBands, cinemaTripforTwo, soccerBall, basketBall, hoodie,
            runningShoes, iPhone, bike;
    private ArrayList<Reward> tierOneRewards, tierTwoRewards;
    private Random random;

    public RewardItems() {
        initiateRewards();
        tierOneRewards = new ArrayList<>();
        tierOneRewards.add(fiveExperience);
        tierOneRewards.add(tenExperience);
        tierOneRewards.add(canteenTenKR);
        tierOneRewards.add(smoothie);
        tierOneRewards.add(juice);
        tierOneRewards.add(rawBar);
        tierOneRewards.add(proteinShake);

        tierTwoRewards = new ArrayList<>();
        tierTwoRewards.add(iPhoneHolder);
        tierTwoRewards.add(drinkBottle);
        tierTwoRewards.add(trainingBands);
        tierTwoRewards.add(cinemaTripforTwo);
        tierTwoRewards.add(soccerBall);
        tierTwoRewards.add(basketBall);
        tierTwoRewards.add(hoodie);
        tierTwoRewards.add(runningShoes);
        tierTwoRewards.add(iPhone);
        tierTwoRewards.add(bike);

        random = new Random();
    }

    private void initiateRewards() {
        fiveExperience = new Reward("Fem XP", 1, R.drawable.plus_five);
        tenExperience = new Reward("Ti XP", 1, R.drawable.plus_ten);
        canteenTenKR = new Reward("10 Kr i Kantinen", 1, R.drawable.prize10kr);
        smoothie = new Reward("Smoothie", 1, R.drawable.ic_drink);
        juice = new Reward("Juice", 1, R.drawable.ic_juice);
        rawBar = new Reward("Raw Bar", 1, R.drawable.ic_energy_bar);
        proteinShake = new Reward("Protein Shake", 1, R.drawable.ic_protein_shake);

        iPhoneHolder = new Reward("iPhone Holder", 2, R.drawable.ic_sport);
        drinkBottle = new Reward("Drikkedunk", 2, R.drawable.ic_drink_bottle);
        trainingBands = new Reward("Trænings Elastik", 2, R.drawable.ic_rubber_bands);
        cinemaTripforTwo = new Reward("Bio tur for 2", 2, R.drawable.ic_cinema);
        soccerBall = new Reward("Fodbold", 2, R.drawable.ic_soccer);
        basketBall = new Reward("Basketball", 2, R.drawable.ic_basketball);
        hoodie = new Reward("Hoodie", 2, R.drawable.ic_sweatshirt);
        runningShoes = new Reward("Løbesko", 2, R.drawable.ic_shoe);
        iPhone = new Reward("iPhone", 2, R.drawable.ic_iphone);
        bike = new Reward("Elcykel", 2, R.drawable.ic_bike);
    }

    public Reward getTierOneReward(){
        Reward reward;
        reward = tierOneRewards.get(random.nextInt(tierOneRewards.size()));
        return reward;
    }

    public Reward getTierTwoReward(){
        Reward reward;
        reward = tierTwoRewards.get(random.nextInt(tierTwoRewards.size()));
        return reward;
    }

}
