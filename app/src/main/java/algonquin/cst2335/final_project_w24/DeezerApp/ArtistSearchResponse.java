package algonquin.cst2335.final_project_w24.DeezerApp;

import java.util.List;

public class ArtistSearchResponse {
    private List<ArtistDetails> data;

    public List<ArtistDetails> getData() {
        return data;
    }

    public void setData(List<ArtistDetails> data) {
        this.data = data;
    }
}
