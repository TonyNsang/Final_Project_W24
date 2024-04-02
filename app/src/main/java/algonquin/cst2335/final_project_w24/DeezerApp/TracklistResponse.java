package algonquin.cst2335.final_project_w24.DeezerApp;

import java.util.List;

public class TracklistResponse {

    private List<Track> data;
    public List<Track> getData() {
        return data;
    }

    public static class Track {
        private long id;
        private boolean readable;
        private String title;
        private String title_short;
        private String title_version;
        private String link;
        private int duration;
        private int rank;
        private boolean explicit_lyrics;
        private int explicit_content_lyrics;
        private int explicit_content_cover;
        private String preview;
        private List<Contributor> contributors;
        private String md5_image;
        private Artist artist;
        private Album album;
        private String type;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean isReadable() {
            return readable;
        }

        public void setReadable(boolean readable) {
            this.readable = readable;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_short() {
            return title_short;
        }

        public void setTitle_short(String title_short) {
            this.title_short = title_short;
        }

        public String getTitle_version() {
            return title_version;
        }

        public void setTitle_version(String title_version) {
            this.title_version = title_version;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public boolean isExplicit_lyrics() {
            return explicit_lyrics;
        }

        public void setExplicit_lyrics(boolean explicit_lyrics) {
            this.explicit_lyrics = explicit_lyrics;
        }

        public int getExplicit_content_lyrics() {
            return explicit_content_lyrics;
        }

        public void setExplicit_content_lyrics(int explicit_content_lyrics) {
            this.explicit_content_lyrics = explicit_content_lyrics;
        }

        public int getExplicit_content_cover() {
            return explicit_content_cover;
        }

        public void setExplicit_content_cover(int explicit_content_cover) {
            this.explicit_content_cover = explicit_content_cover;
        }

        public String getPreview() {
            return preview;
        }

        public void setPreview(String preview) {
            this.preview = preview;
        }

        public List<Contributor> getContributors() {
            return contributors;
        }

        public void setContributors(List<Contributor> contributors) {
            this.contributors = contributors;
        }

        public String getMd5_image() {
            return md5_image;
        }

        public void setMd5_image(String md5_image) {
            this.md5_image = md5_image;
        }

        public Artist getArtist() {
            return artist;
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }

        public Album getAlbum() {
            return album;
        }

        public void setAlbum(Album album) {
            this.album = album;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class Contributor {
            private long id;
            private String name;
            private String link;
            private String share;
            private String picture;
            private String picture_small;
            private String picture_medium;
            private String picture_big;
            private String picture_xl;
            private boolean radio;
            private String tracklist;
            private String type;
            private String role;

            // Getters and setters

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getShare() {
                return share;
            }

            public void setShare(String share) {
                this.share = share;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getPicture_small() {
                return picture_small;
            }

            public void setPicture_small(String picture_small) {
                this.picture_small = picture_small;
            }

            public String getPicture_medium() {
                return picture_medium;
            }

            public void setPicture_medium(String picture_medium) {
                this.picture_medium = picture_medium;
            }

            public String getPicture_big() {
                return picture_big;
            }

            public void setPicture_big(String picture_big) {
                this.picture_big = picture_big;
            }

            public String getPicture_xl() {
                return picture_xl;
            }

            public void setPicture_xl(String picture_xl) {
                this.picture_xl = picture_xl;
            }

            public boolean isRadio() {
                return radio;
            }

            public void setRadio(boolean radio) {
                this.radio = radio;
            }

            public String getTracklist() {
                return tracklist;
            }

            public void setTracklist(String tracklist) {
                this.tracklist = tracklist;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }
        }

        public static class Artist {
            private long id;
            private String name;
            private String tracklist;
            private String type;

            // Getters and setters

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTracklist() {
                return tracklist;
            }

            public void setTracklist(String tracklist) {
                this.tracklist = tracklist;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class Album {
            private long id;
            private String title;
            private String cover;
            private String cover_small;
            private String cover_medium;
            private String cover_big;
            private String cover_xl;
            private String md5_image;
            private String tracklist;
            private String type;

            // Getters and setters

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getCover_small() {
                return cover_small;
            }

            public void setCover_small(String cover_small) {
                this.cover_small = cover_small;
            }

            public String getCover_medium() {
                return cover_medium;
            }

            public void setCover_medium(String cover_medium) {
                this.cover_medium = cover_medium;
            }

            public String getCover_big() {
                return cover_big;
            }

            public void setCover_big(String cover_big) {
                this.cover_big = cover_big;
            }

            public String getCover_xl() {
                return cover_xl;
            }

            public void setCover_xl(String cover_xl) {
                this.cover_xl = cover_xl;
            }

            public String getMd5_image() {
                return md5_image;
            }

            public void setMd5_image(String md5_image) {
                this.md5_image = md5_image;
            }

            public String getTracklist() {
                return tracklist;
            }

            public void setTracklist(String tracklist) {
                this.tracklist = tracklist;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }

}
