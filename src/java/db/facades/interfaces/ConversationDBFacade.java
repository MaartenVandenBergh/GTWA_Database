/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.facades.interfaces;

import domain.entities.Conversation;
import java.util.List;

/**
 *
 * @author Maarten
 */
public interface ConversationDBFacade{

    public Conversation create(Conversation c);
    public Conversation edit(Conversation c);
    public void remove(Conversation c);
    public Conversation findConversation(long id);
    public List<Conversation> findAllConversations();
}
