package it.unibo.deathnote.api;

import java.util.List;

/**
 * A DeathNote is a special book intended to be used by Shinigamis (Death gods) to kill humans,
 * thereby extending their own lives. Each DeathNote has a set of rules that must be followed
 * in order to use it properly.
 */
public interface DeathNote {

    /**
     * Returns the list of rules for this DeathNote.
     */
    List<String> RULES = List.of(
        """
        The human whose name is written in this note shall die.
        """,
        """
        This note will not take effect unless the writer has the subject's face in mind when
        writing his/her name. This is to prevent people who share the same name from being
        affected.
        """,
        """
        After writing the cause of death, details of the death should be written in the next 6
        seconds and 40 milliseconds.
        """,
        """
        The human who touches the Death Note can recognize the image and voice of its original
        owner, a god of death, even if the human is not the owner of the note.
        """,
        """
        The person in possession of the Death Note is possessed by a god of death,
        its original owner, until they die.
        """,
        """
        Gods of death, the original owners of the Death Note, do not do, in principle,
        anything which will help or prevent the deaths in the note. A god of death has no
        obligation to completely explain how to use the note or rules which will apply to the
        human who owns it unless asked.
        """,
        """
        A god of death can extend their own life by putting a name on their own note, but
        humans cannot.
        """,
        """
        The human who becomes the owner of the Death Note can, in exchange of half his/her
        remaining life, get the eyeballs of the god of death which will enable him/her to see
        a human's name and remaining life span when looking through them.
        """,
        """
        The conditions for death will not be realized unless it is physically possible for
        that human or it is reasonably assumed to be carried out by that human.
        """,
        """
        One page taken from the Death Note, or even a fragment of the page, contains the full
        effects of the note.
        """,
        """
        The individuals who lose the ownership of the Death Note will also lose their memory
        of the usage of the Death Note. This does not mean that he will lose all the memory
        from the day he owned it to the day he loses possession, but means he will only lose
        the memory involving the Death Note.
        """,
        """
        The number of pages of the Death Note will never run out.
        """,
        """
        It is useless trying to erase names written in the Death Note with erasers or
        white-out.
        """
    );

    /**
     * Returns the rule with the given number.
     *
     * @param ruleNumber the number of the rule to return. The first rule has number one
     * @return the rule with the given number
     * @throws IllegalArgumentException if the given rule number is smaller than 1 or larger
     *     than the number of rules
     */
    String getRule(int ruleNumber);

    /**
     * The human whose name is written in this DeathNote will die.
     *
     * @param name the name of the human to kill
     * @throws NullPointerException if the given name is null.
     */
    void writeName(String name);

    /**
     * If the cause of death is written within the next 40 milliseconds of writing the person's
     * name, it will happen.
     *
     * @param cause the cause of the human's death
     * @return true if the cause was written within 40 milliseconds, false otherwise
     * @throws IllegalStateException if there is no name written in this DeathNote,
     *     or the cause is null
     */
    boolean writeDeathCause(String cause);

    /**
     * After writing the cause of death, details of the death should be written in the next
     * 6 seconds and 40 milliseconds.
     *
     * @param details the details of the human's death
     * @return true if the details were written within 6 seconds and 40 milliseconds, false otherwise
     * @throws IllegalStateException if there is no name written in this DeathNote,
     *     or the details are null
     */
    boolean writeDetails(String details);

    /**
     * Provides the cause of death of the person with the given name.
     *
     * @param name the name of the person whose death cause to return
     * @return the death cause of the person with the given name.
     *     If the cause of death is not specified, the method will return "heart attack".
     * @throws IllegalArgumentException if the provider name is not written in this DeathNote
     */
    String getDeathCause(String name);

    /**
     * Provides the details of the death of the person with the given name.
     *
     * @param name the name of the person whose death cause to return
     * @return the death details of the person with the given name,
     *     or an empty string if no details have been provided.
     * @throws IllegalArgumentException if the provider name is not written in this DeathNote.
     */
    String getDeathDetails(String name);

    /**
     * Checks if the given name is written in this DeathNote.
     *
     * @param name the name of the person
     * @return true if the given name is written in this DeathNote, false otherwise
     */
    boolean isNameWritten(String name);
}
