fun main() {
    val firstNote = Note(
        ownerId = 12,
        title = "First note",
        text = "Text",
        date = 2022_02_11,
        comments = emptyList(),
        deletedComments = emptyList()
    )

    val secondNote = Note(
        ownerId = 21,
        title = "Second note",
        text = "Text",
        date = 2022_02_12,
        comments = emptyList(),
        deletedComments = emptyList()
    )

    val thirdNote = Note(
        ownerId = 33,
        title = "Third note",
        text = "Text",
        date = 2022_02_13,
        comments = emptyList(),
        deletedComments = emptyList()
    )

    val firstComment = Comment(id = 1, noteId = 1, date = 2022_02_10, text = "First comment")
    val secondComment = Comment(id = 2, noteId = 2, date = 2022_02_11, text = "Second comment")
    val thirdComment = Comment(id = 3, noteId = 3, date = 2022_02_12, text = "Third comment")
    val fourthComment = Comment(id = 4, noteId = 3, date = 2022_02_14, text = "Fourth comment")
    val fifthComment = Comment(id = 5, noteId = 3, date = 2022_02_15, text = "Fifth comment")



    val editedNote = Note(
        id = 1,
        ownerId = 555,
        title = "Edit",
        text = "That's edited text", date = 2022_02_15,
        emptyList(),
        emptyList()
    )

    val editedComment = Comment(id = 3, noteId = 3,date = 2022_02_13, text = "Edited comment!")

    NoteService.add(firstNote)
    NoteService.add(secondNote)
    NoteService.add(thirdNote)

    NoteService.createComment(firstComment)
    NoteService.createComment(secondComment)
    NoteService.createComment(thirdComment)
    NoteService.createComment(fourthComment)
    NoteService.createComment(fifthComment)

    NoteService.deleteNote(2)
    NoteService.deleteComment(1)

    NoteService.editNote(editedNote)
    NoteService.editComment(editedComment)

    NoteService.recoveryComment(1)
}