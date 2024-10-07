fun rooms(rooms: List<Room>) =
    apply {
        rooms.elementAtOrNull(0)?.let {
            this.room1Adults = it.adults
            this.room1ChildAges = it.childAges?.joinToString(",")
        }

        rooms.elementAtOrNull(1)?.let {
            this.room2Adults = it.adults
            this.room2ChildAges = it.childAges?.joinToString(",")
        }

        rooms.elementAtOrNull(2)?.let {
            this.room3Adults = it.adults
            this.room3ChildAges = it.childAges?.joinToString(",")
        }

        rooms.elementAtOrNull(3)?.let {
            this.room4Adults = it.adults
            this.room4ChildAges = it.childAges?.joinToString(",")
        }

        rooms.elementAtOrNull(4)?.let {
            this.room5Adults = it.adults
            this.room5ChildAges = it.childAges?.joinToString(",")
        }

        rooms.elementAtOrNull(5)?.let {
            this.room6Adults = it.adults
            this.room6ChildAges = it.childAges?.joinToString(",")
        }

        rooms.elementAtOrNull(6)?.let {
            this.room7Adults = it.adults
            this.room7ChildAges = it.childAges?.joinToString(",")
        }

        rooms.elementAtOrNull(7)?.let {
            this.room8Adults = it.adults
            this.room8ChildAges = it.childAges?.joinToString(",")
        }
    }
