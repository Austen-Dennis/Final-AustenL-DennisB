package finals.project.smsPage

class ChatMessage(val id: String, val text: String, val fromId: String, val toID: String, val timestamp: Long) {
    constructor() : this("", "", "", "", -1)
}