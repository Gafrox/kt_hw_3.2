import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {

    @Test
    fun add() {
        val note = Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        )
        val result = NoteService.add(note)
        assert(result.id != 0)
    }

    @Test
    fun createComment_True() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        val comment = Comment(id = 1, noteId = 1, date = 2022_02_01, text = "First comment")
        val result = NoteService.createComment(comment)
        assertTrue(result)
    }

    @Test
    fun createComment_False() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        val comment = Comment(id = 1, noteId = 0, date = 2022_02_02, text = "First comment")
        val result = NoteService.createComment(comment)
        assertFalse(result)
    }

    @Test
    fun deleteNote_True() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.add(Note(
            ownerId = 21,
            title = "Second note",
            text = "Text",
            date = 2022_02_12,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        val result = NoteService.deleteNote(2)
        assertTrue(result)
    }

    @Test
    fun deleteNote_InputError() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        val result = NoteService.deleteNote(5)
        assertFalse(result)
    }

    @Test
    fun deleteNote_ReDeletion() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_2_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.add(Note(
            ownerId = 21,
            title = "Second note",
            text = "Text",
            date = 2022_02_12,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.deleteNote(2)
        val result = NoteService.deleteNote(2)
        assertFalse(result)
    }

    @Test
    fun deleteComment_True() {
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
            date = 2022_02_12,
            comments = emptyList(),
            deletedComments = emptyList()
        )
        val firstComment = Comment(id = 1, noteId = 3, date = 2022_02_01, text = "First comment")
        val secondComment = Comment(id = 2, noteId = 3, date = 2022_02_01, text = "Second comment")
        val thirdComment = Comment(id = 3, noteId = 3, date = 2022_02_01, text = "Third comment")
        val fourthComment = Comment(id = 4, noteId = 3, date = 2022_02_01, text = "Fourth comment")
        NoteService.add(firstNote)
        NoteService.add(secondNote)
        NoteService.add(thirdNote)
        NoteService.createComment(firstComment)
        NoteService.createComment(secondComment)
        NoteService.createComment(thirdComment)
        NoteService.createComment(fourthComment)
        val result = NoteService.deleteComment(commentId = 4)
        assertTrue(result)
    }

    @Test
    fun deleteComment_ReDeletion() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        val firstComment = Comment(id = 1, noteId = 1, date = 2022_02_01, text = "First comment")
        val secondComment = Comment(id = 2, noteId = 1, date = 2022_02_01, text = "Second comment")
        NoteService.createComment(firstComment)
        NoteService.createComment(secondComment)
        NoteService.deleteComment(commentId = 2)
        val result = NoteService.deleteComment(commentId = 2)
        assertFalse(result)
    }

    @Test
    fun editNote_True() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        val editedNote = Note(
            id = 1,
            ownerId = 555,
            title = "Edit",
            text = "That's edited text", date = 2022_02_15,
            emptyList(),
            emptyList()
        )
        val result = NoteService.editNote(editedNote)
        assertTrue(result)
    }

    @Test
    fun editNote_False() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.add(Note(
            ownerId = 21,
            title = "Second note",
            text = "Text",
            date = 2022_02_12,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        val editedNote = Note(
            id = 2,
            ownerId = 555,
            title = "Edit",
            text = "That's edited text", date = 2022_02_15,
            emptyList(),
            emptyList()
        )
        NoteService.deleteNote(noteId = 2)
        val result = NoteService.editNote(editedNote)
        assertFalse(result)
    }

    @Test
    fun editNote_IdError() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.add(Note(
            ownerId = 21,
            title = "Second note",
            text = "Text",
            date = 2022_02_12,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        val editedNote = Note(
            id = 5,
            ownerId = 555,
            title = "Edit",
            text = "That's edited text", date = 2022_02_15,
            emptyList(),
            emptyList()
        )
        val result = NoteService.editNote(editedNote)
        assertFalse(result)
    }

    @Test
    fun editComment_True() {
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
        NoteService.add(firstNote)
        NoteService.add(secondNote)

        val firstComment  = Comment(id = 1, noteId = 8, date = 2022_02_01, text = "First comment")
        val secondComment = Comment(id = 2, noteId = 8, date = 2022_02_01, text = "Second comment")

        NoteService.createComment(firstComment)
        NoteService.createComment(secondComment)

        val editedComment = Comment(id = 2, noteId = 8, date = 2022_02_01, text = "Edited comment!")
        val result = NoteService.editComment(editedComment)
        assertTrue(result)
    }

    @Test
    fun editComment_DeletedCommentError() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.add(Note(
            ownerId = 21,
            title = "Second note",
            text = "Text",
            date = 2022_02_12,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.add(Note(
            ownerId = 33,
            title = "Third note",
            text = "Text",
            date = 2022_09_13,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.createComment(Comment(id = 1, noteId = 3, date = 2022_02_01, text = "First comment"))
        NoteService.createComment(Comment(id = 2, noteId = 3, date = 2022_02_01, text = "Second comment"))
        NoteService.createComment(Comment(id = 3, noteId = 3, date = 2022_02_01, text = "Third comment"))

        NoteService.deleteComment(commentId = 3)
        val editedComment = Comment(id = 3, noteId = 1, date = 2022_02_01, text = "Edited comment!")
        val result = NoteService.editComment(editedComment)
        assertFalse(result)
    }

    @Test
    fun editComment_DeletedNoteError() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.createComment(Comment(id = 1, noteId = 1, date = 2022_02_01, text = "First comment"))
        NoteService.deleteNote(noteId = 1)
        val editedComment = Comment(id = 1, noteId = 1, date = 2022_02_01, text = "Edited comment!")
        val result = NoteService.editComment(editedComment)
        assertFalse(result)
    }

    @Test
    fun editComment_IdError() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.createComment(Comment(id = 1, noteId = 1, date = 2022_02_01, text = "First comment"))
        NoteService.createComment(Comment(id = 2, noteId = 2, date = 2022_02_01, text = "Second comment"))
        val editedComment = Comment(id = 5, noteId = 1, date = 2022_02_01, text = "Edited comment!")
        val result = NoteService.editComment(editedComment)
        assertFalse(result)
    }

    @Test
    fun recoveryComment_True() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.add(Note(
            ownerId = 21,
            title = "Second note",
            text = "Text",
            date = 2022_02_12,
            comments = emptyList(),
            deletedComments = emptyList()
        ))

        NoteService.createComment(Comment(id = 1, noteId = 4, date = 2020_09_01, text = "First comment"))
        NoteService.createComment(Comment(id = 2, noteId = 4, date = 2020_09_01, text = "Second comment"))

        NoteService.deleteComment(2)
        val result = NoteService.recoveryComment(commentId = 2)
        assertTrue(result)
    }

    @Test
    fun recoveryComment_NotDeletedComment() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.createComment(Comment(id = 1, noteId = 1, date = 2022_02_01, text = "First comment"))
        val result = NoteService.recoveryComment(commentId = 1)
        assertFalse(result)
    }

    @Test
    fun restoreComment_DeletedNoteError() {
        NoteService.add(Note(
            ownerId = 12,
            title = "First note",
            text = "Text",
            date = 2022_02_11,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.add(Note(
            ownerId = 21,
            title = "Second note",
            text = "Text",
            date = 2022_02_12,
            comments = emptyList(),
            deletedComments = emptyList()
        ))
        NoteService.createComment(Comment(id = 1, noteId = 2, date = 2022_02_01, text = "First comment"))
        NoteService.createComment(Comment(id = 2, noteId = 2, date = 2022_02_01, text = "Second comment"))
        NoteService.deleteNote(2)
        val result = NoteService.recoveryComment(commentId = 2)
        assertFalse(result)
    }
}