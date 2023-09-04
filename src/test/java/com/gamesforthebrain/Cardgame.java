package com.gamesforthebrain;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Cardgame {

    public static void main(String[] args) {
        
        String baseUrl = "https://deckofcardsapi.com/";

      
        Response response = RestAssured.given()
                .when()
                .get(baseUrl);

        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("The site is up and running.");
        } else {
            System.out.println("The site is not responding properly. Status code: " + statusCode);
            return;
        }

      
        response = RestAssured.given()
                .when()
                .get(baseUrl + "api/deck/new/");

        String deckId = response.path("deck_id");

      
        RestAssured.given()
                .when()
                .get(baseUrl + "api/deck/" + deckId + "/shuffle/");

       
        response = RestAssured.given()
                .when()
                .get(baseUrl + "api/deck/" + deckId + "/draw/?count=6");

       
        String card1 = response.path("cards[0].value");
        String card2 = response.path("cards[1].value");
        String card3 = response.path("cards[2].value");
        boolean player1HasBlackjack = hasBlackjack(card1, card2, card3);

        String card4 = response.path("cards[3].value");
        String card5 = response.path("cards[4].value");
        String card6 = response.path("cards[5].value");
        boolean player2HasBlackjack = hasBlackjack(card4, card5, card6);

       
        if (player1HasBlackjack || player2HasBlackjack) {
            if (player1HasBlackjack && player2HasBlackjack) {
                System.out.println("Both players have blackjack!");
            } else if (player1HasBlackjack) {
                System.out.println("Player 1 has blackjack!");
            } else {
                System.out.println("Player 2 has blackjack!");
            }
        } else {
            System.out.println("Neither player has blackjack.");
        }
    }

    public static boolean hasBlackjack(String card1, String card2, String card3) {
        return (isTenCard(card1) && isAceCard(card2)) || (isTenCard(card2) && isAceCard(card1)) ||
               (isTenCard(card1) && isAceCard(card3)) || (isTenCard(card3) && isAceCard(card1)) ||
               (isTenCard(card2) && isAceCard(card3)) || (isTenCard(card3) && isAceCard(card2));
    }

    public static boolean isTenCard(String cardValue) {
        return cardValue.equals("10") || cardValue.equals("JACK") || cardValue.equals("QUEEN") || cardValue.equals("KING");
    }

    public static boolean isAceCard(String cardValue) {
        return cardValue.equals("ACE");
    }
}
