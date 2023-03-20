package finals.project.smsPage

import java.util.*

class ChatMessage(toString: String, s1: String) {
    var messageText: String? = null
    var messageUser: String? = null
    private var messageTime: Long = 0
    fun ChatMessage(messageText: String?, messageUser: String?) {
        this.messageText = messageText
        this.messageUser = messageUser
        // Initialize to current time
        messageTime = Date().time
    }

    fun ChatMessage() {}
    fun getMessageText(): String? {
        return messageText
    }

    fun setMessageText(messageText: String?) {
        this.messageText = messageText
    }

    fun getMessageUser(): String? {
        return messageUser
    }

    fun setMessageUser(messageUser: String?) {
        this.messageUser = messageUser
    }

    fun getMessageTime(): Long {
        return messageTime
    }

    fun setMessageTime(messageTime: Long) {
        this.messageTime = messageTime
    }

}