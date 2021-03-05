package com.game21;

import java.util.Random;
import java.util.Scanner;

public class game {
    private static Players player1 = new Players();
    private static Players pc = new Players();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        player1.setName("Гравець");
        player1.setBalance(1000);
        pc.setName("Компютер");
        pc.setBalance(1000);
        String playerChoose = startGameAgreement();
        boolean canStartGame = validatePlayerChoose(playerChoose);
        boolean playAgain1 = true;
        while(playAgain1) {
            if (canStartGame) {
                boolean isStartGame = true;
                printStartGameText();
                playerCard(randomCardFromDeck(), randomCardFromDeck(), player1.getBalance());
                pcCard(randomCardFromDeck(), randomCardFromDeck(), pc.getBalance(), pc.getName());
                while(isStartGame) {
//                    printCurrentScore();
                    String playerChoose2 = moreOrOpen();
                    if (playerChoose2.equalsIgnoreCase("+")) {
                        getPlayerCard(randomCardFromDeck());
                        pcPlayerGetCard();
                    } else if (playerChoose2.equalsIgnoreCase("-")) {
                        isStartGame = false;
                        validateBalance();
                        validateResult();
                        printCurrentScore();
                        printPlayersBalance();
                        System.out.println("------------------");
                    }
                }
            }
            String playAgain = tryAgainGame();
            boolean newGame = validateNewGame(playAgain);
            if (!newGame || player1.getBalance() == 0) {
                printPlayersBalance();
                System.out.println("Гру завершено.");
                playAgain1 = false;
            } else if (pc.getBalance() == 0) {
                printPlayersBalance();
                System.out.println("Гру завершено.");
                playAgain1 = false;
            }
        }
    }


    public static void printPlayersBalance() {
        System.out.println("Баланс " + player1.getName() + " = " + player1.getBalance());
        System.out.println("Баланс " + pc.getName() + " = " + pc.getBalance());
    }

    public static void validateBalance() {
        if (player1.getScore() > 21) {
            int newPlBalance = player1.getBalance() - 100;
            player1.setBalance(newPlBalance);
            int newPcBalance = pc.getBalance() + 100;
            pc.setBalance(newPcBalance);
        } else if (pc.getScore() > 21) {
            int newPlBalance = player1.getBalance() + 100;
            player1.setBalance(newPlBalance);
            int newPcBalance = pc.getBalance() - 100;
            pc.setBalance(newPcBalance);
        } else if (player1.getScore() <= 21 && player1.getScore() > pc.getScore()) {
            int newPlBalance = player1.getBalance() + 100;
            player1.setBalance(newPlBalance);
            int newPcBalance = pc.getBalance() - 100;
            pc.setBalance(newPcBalance);
        } else if (pc.getScore() <= 21 && pc.getScore() > player1.getScore()) {
            int newPlBalance = player1.getBalance() - 100;
            player1.setBalance(newPlBalance);
            int newPcBalance = pc.getBalance() + 100;
            pc.setBalance(newPcBalance);
        }
    }

    public static void validateResult() {
        if (player1.getScore() > 21) {
            System.out.println(player1.getName() + " програв");
        } else if (pc.getScore() > 21) {
            System.out.println(pc.getName() + " програв");
        } else if (player1.getScore() <= 21 && player1.getScore() > pc.getScore()) {
            System.out.println(player1.getName() + " виграв");
        } else if (pc.getScore() <= 21 && pc.getScore() > player1.getScore()) {
            System.out.println(pc.getName() + " виграв");
        }

    }

    public static void pcPlayerGetCard() {
        if (pc.getScore() < 18) {
            int randomCard = randomCardFromDeck();
            int newPcScore = pc.getScore() + randomCard;
            pc.setScore(newPcScore);

        }
    }

    public static void printCurrentScore() {
        System.out.println(player1.getName() + " сума карт: " + player1.getScore());
        System.out.println(pc.getName() + " сума карт: " + pc.getScore());
    }


    public static void pcCard(int card, int card2, int balance, String name) {
//        System.out.println("Карти"+ " "+ name + ":"+" "+ card +" "+card2 );
        int sum = card + card2;
        pc.setScore(sum);
//        System.out.println("очки:"+" "+ sum);
//        System.out.println("Баланс = "+" "+ balance+"$");

    }

    public static void getPlayerCard(int card) {
        int currentScore = player1.getScore();
        int newScore = currentScore + card;
        player1.setScore(newScore);
        System.out.println("Нова карта : " + card);
        System.out.println("Ваші очки: " + player1.getScore());

    }

    public static void playerCard(int card, int card2, int balance) {
        System.out.println("Карти роздано!");
        System.out.println("Ваші карти:" + " " + card + " " + card2);
        int sum = card + card2;
        player1.setScore(sum);
        System.out.println(" очки:" + " " + sum);
        System.out.println("Баланс = " + " " + balance + "$");


    }


    public static boolean validateNewGame(String tryAgain) {
        if (tryAgain.equalsIgnoreCase("Y")) {
            return true;
        } else if (tryAgain.equalsIgnoreCase("N")) {
            System.out.println("До наступної гри");
            return false;
        } else System.out.println("Wrong button... ");
        return false;
    }

    public static String tryAgainGame() {
        System.out.println("Зіграти ще раз?  (Y/N)");
        String tryAgain = sc.nextLine();
        return tryAgain;
    }


    public static String moreOrOpen() {
        System.out.print("ще карту натисніть +" + " ");
        System.out.println("відкрити карти натисніть - ");
        String moreOrOpen = sc.nextLine();
        return moreOrOpen;
    }

    public static void printStartGameText() throws InterruptedException {
        System.out.println("Підготуйтеся гра скоро почнеться ...");

        System.out.println("Гравці готові?");
        Thread.sleep(1200);

        System.out.println("Стартуємо");
        Thread.sleep(1000);
        System.out.println("Старт");
    }

    public static boolean validatePlayerChoose(String playerChoose) {
        if (playerChoose.equalsIgnoreCase("start")) {
            return true;
        } else if (playerChoose.equalsIgnoreCase("Exit")) {
            System.out.println("Бувай. пограємо наступного разу");
            return false;
        } else {
            System.out.println("Невідома команда.. Error");
            return false;
        }
    }

    public static String startGameAgreement() {
        System.out.println("Якщо Ви хочете зіграти в ігру 21 введіть Start");
        System.out.println("Якщо Ви хочете зіграти в ігру 21 іншим разом введіть Exit");
        String chooseStart = sc.nextLine();
        return chooseStart;
    }

    public static int randomCardFromDeck() {
        Random random = new Random();
        int min = 2;
        int max = 11;
        return random.nextInt(max - min) + min;
    }

}
