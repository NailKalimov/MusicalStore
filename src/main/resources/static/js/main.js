function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var albumApi = Vue.resource("/albums/{/id}");

Vue.component('album-form', {
    props: ['albums', 'albumAttr'],
    data: function () {
        return {
            albumName: '',
            releaseDate: '',
            id: ''
        }

    },
    watch: {
        albumAttr: function (newVal, oldVal) {
            this.albumName = newVal.albumName;
            this.releaseDate = newVal.releaseDate;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write album name" v-model="albumName"/>' +
        '<input type="text" placeholder="Write release date" v-model="releaseDate"/>' +
        '<input type="button" value="Save" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            var album = {albumName: this.albumName, releaseDate: this.releaseDate}

            if (this.id) {
                albumApi.update({id: this.id}, album).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.albums, data.id)
                        this.albums.splice(index, 1, data)
                        this.albumName = '';
                        this.releaseDate = '';
                        this.id = '';
                    }));
            } else {
                albumApi.save({}, album).then(result =>
                    result.json().then(data => {
                        this.albums.push(data);
                        this.albumName = '';
                        this.releaseDate = '';
                    }));
            }
        }
    }
});

Vue.component('album-row', {
    props: ['album', 'editMethod', "albums"],
    template: '<div>' +
        '<i>({{album.id}})</i> {{album.albumName}} {{album.releaseDate}}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="Delete" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.album);
        },
        del: function() {
            albumApi.remove({id: this.album.id}).then(result => {
                if (result.ok) {
                    this.albums.splice(this.albums.indexOf(this.album), 1)
                }
            })
        }
    }
});

Vue.component('albums-list', {
    props: ['albums'],
    data: function () {
        return {
            album: null
        }
    },
    template:
        '<div style="position: relative; width: 400px">' +
        '<album-form :albums=albums :albumAttr="album"/>' +
        '<album-row v-for="album in albums" :key="album.id" :album="album"' +
            ' :editMethod="editMethod" :albums="albums"/>' +
        '</div>',
    created: function () {
        albumApi.get().then(result =>
            result.json().then(data =>
                data.forEach(album => this.albums.push(album))
            )
        )
    },
    methods: {
        editMethod: function (album) {
            this.album = album;
        }
    }
});

var app = new Vue({
    el: '#AlbumFront',
    template: '<albums-list :albums= "albums" />',
    data: {
        albums: []
    }
});




