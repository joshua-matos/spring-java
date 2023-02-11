import { Grid, Stack, Toolbar } from "@mui/material";
import React from "react";
import AppbarContainer from "./common/AppbarContainer";

function App() {
  return (
    <Stack>
      <AppbarContainer />
      <Toolbar />
      <Grid
        container
        spacing={3}
        alignItems="center"
        sx={{
          height: "100%",
          alignItems: "top",
        }}
      >
        <Grid item xs={12}></Grid>
      </Grid>
    </Stack>
  );
}

export default App;
