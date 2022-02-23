object NoteService {
    private var notes = mutableListOf<Note>()
    private var deletedNotes = mutableListOf<Note>()
    private var lastId = 1


    fun add(note: Note): Note {
        notes.plusAssign(note.copy(id = lastId++))
        return notes.last()
    }

    fun createComment(comment: Comment): Boolean {
        for (n: Note in notes) {

            if (n.id == comment.noteId) {
                val tempListOfComments = (n.comments).toMutableList()
                tempListOfComments.add(comment)
                val updatedNote = n.copy(comments = tempListOfComments)
                notes[notes.indexOf(n)] = updatedNote
                return true
            }
        }
        return false
    }

    fun deleteNote(noteId: Int): Boolean {
        if (noteId < 0 || noteId > notes.size) {
            println("Неверный Id, повторите ввод!")
            return false
        }

        for (note: Note in deletedNotes) {
            if (noteId == note.id) {
                println("Заметка с Id = $noteId не найдена!")
                return false
            }
        }

        for (note: Note in notes) {
            if (noteId == note.id) {
                deletedNotes.add(note)
                notes.remove(note)
                return true
            }
        }
        return false
    }

    fun deleteComment(commentId: Int): Boolean {
        for (note: Note in notes) {
            for (delComm: Comment in note.deletedComments) {
                if (commentId == delComm.id) {
                    println("Комментарий с Id= $commentId не найден!")
                    return false
                }
            }

            for (comm: Comment in note.comments) {
                if (commentId == comm.id) {
                    val tempListOfComments = note.comments.toMutableList()
                    tempListOfComments.remove(comm)
                    val tempListOfDeletedComments = note.deletedComments.toMutableList()
                    tempListOfDeletedComments.plusAssign(comm)
                    val updatedNote = note.copy(
                        comments = tempListOfComments,
                        deletedComments = tempListOfDeletedComments
                    )
                    notes[notes.indexOf(note)] = updatedNote
                    return true
                }
            }
        }
        return false
    }

    fun editNote(note: Note): Boolean {
        for (n: Note in deletedNotes) {
            if (n.id == note.id) {
                println("Заметка удалена!")
                return false
            }
        }

        for (n: Note in notes) {
            val noteIndexInList = notes.indexOf(n)

            return if (n.id != note.id) {
                println("Заметки с Id = ${note.id} не существует!")
                false
            } else {
                notes[noteIndexInList] = note.copy(
                    id = n.id,
                    comments = n.comments,
                    deletedComments = n.deletedComments
                )
                true
            }
        }
        return false
    }

    fun editComment(comment: Comment): Boolean {
        for (note: Note in notes) {
            for (comm: Comment in note.deletedComments) {
                if (comm.id == comment.id) {
                    println("Комментарий удалён!")
                    return false
                }
            }
        }

        for (note: Note in deletedNotes) {
            for (comm: Comment in note.comments) {
                if (comment.id == comm.id) {
                    println("Заметка с комментарием (Id = ${comment.id}) удалена!!")
                    return false
                }
            }
        }

        for (note: Note in notes) {
            val tempListOfComments = note.comments.toMutableList()
            for (comm: Comment in note.comments) {
                if (comm.id == comment.id) {
                    tempListOfComments[note.comments.indexOf(comm)] = comment.copy(
                        id = comm.id,
                        noteId = comm.noteId,
                    )
                    notes[notes.indexOf(note)] = note.copy(comments = tempListOfComments)
                    return true
                }
            }
        }
        return false
    }

    fun recoveryComment(commentId: Int): Boolean {
        for (note: Note in deletedNotes) {
            for (comm: Comment in note.comments) {
                if (commentId == comm.id) {
                    println("Комментарий с Id = $commentId удалён!")
                    return false
                }
            }
        }

        for (note: Note in notes) {
            for (comm: Comment in note.comments) {
                if(comm.id == commentId) {
                    println("Комментарий не удалён!")
                }
            }
        }

        for (note: Note in notes) {
            for (comm: Comment in note.deletedComments) {
                if (commentId == comm.id) {
                    val tempListOfComments = note.comments.toMutableList()
                    val tempListOfDeletedComments = note.deletedComments.toMutableList()
                    tempListOfDeletedComments.remove(comm)
                    tempListOfComments.plusAssign(comm)
                    val updatedNote = note.copy(
                        comments = tempListOfComments,
                        deletedComments = tempListOfDeletedComments
                    )
                    notes[notes.indexOf(note)] = updatedNote
                    return true
                }
            }
        }
        return false
    }
}