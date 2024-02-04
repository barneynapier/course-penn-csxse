package hw2;

import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator {

    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {

        // Create a stack for the open tags
        Stack<HtmlTag> openTags = new Stack<HtmlTag>();
        // For each html tag
        for (HtmlTag tag: tags) {
            // Open tag --> Add to top
            if (tag.isOpenTag()) {
                openTags.push(tag);
            }
            // Close tag --> Check vs top
            else {
                if (!tag.isSelfClosing()) {
                    if (openTags.isEmpty()) { 
                        return null;
                    }

                    if (tag.matches(openTags.peek())) {
                        openTags.pop();
                    } else {
                        return openTags;
                    }
                }
            }
        }

        return openTags;
    }
}

