package academy.learnprogramming.scopes;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;

@ConversationScoped
public class ConversationScope implements Serializable {
    private static final long serialVersionUID = 215478455465524L;
}
