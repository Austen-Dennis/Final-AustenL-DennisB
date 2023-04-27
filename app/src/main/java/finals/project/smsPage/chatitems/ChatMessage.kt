package finals.project.smsPage.chatitems

class ChatMessage(val id: String, val text: String, val fromId: String, val toID: String, val timestamp: Long) {
    constructor() : this("", "", "", "", -1)
}