function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var artistApi = Vue.resource("/artists/{/id}");

Vue.component('artist-form', {
    props: ['artists', 'artistAttr'],
    data: function () {
        return {
            artistName: '',
            id: ''
        }

    },
    watch: {
        artistAttr: function (newVal) {
            this.artistName = newVal.artistName;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write artist name" v-model="artistName"/>' +
        '<input type="button" value="Save" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            var artist = {artistName: this.artistName}

            if (this.id) {
                artistApi.update({id: this.id}, artist).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.artists, data.id)
                        this.artists.splice(index, 1, data)
                        this.artistName = '';
                        this.id = '';
                    }));
            } else {
                artistApi.save({}, artist).then(result =>
                    result.json().then(data => {
                        this.artists.push(data);
                        this.artistName = '';
                    }));
            }
        }
    }
});

Vue.component('artist-row', {
    props: ['artist', 'editMethod', "artists"],
    template: '<div>' +
        '<i>({{artist.id}})</i> {{artist.artistName}}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="Delete" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.artist);
        },
        del: function() {
            artistApi.remove({id: this.artist.id}).then(result => {
                if (result.ok) {
                    this.artists.splice(this.artists.indexOf(this.artist), 1)
                }
            })
        }
    }
});

Vue.component('artists-list', {
    props: ['artists'],
    data: function () {
        return {
            artist: null
        }
    },
    template:
        '<div style="position: relative; width: 400px">' +
        '<artist-form :artists=artists :artistAttr="artist"/>' +
        '<artist-row v-for="artist in artists" :key="artist.id" :artist="artist"' +
        ' :editMethod="editMethod" :artists="artists"/>' +
        '</div>',
    created: function () {
        artistApi.get().then(result =>
            result.json().then(data =>
                data.forEach(artist => this.artists.push(artist))
            )
        )
    },
    methods: {
        editMethod: function (artist) {
            this.artist = artist;
        }
    }
});

var app = new Vue({
    el: '#ArtistFront',
    template: '<artists-list :artists= "artists" />',
    data: {
        artists: []
    }
});




