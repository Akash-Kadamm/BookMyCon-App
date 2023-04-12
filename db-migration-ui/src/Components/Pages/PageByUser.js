import React from "react";
import { useState } from "react";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import "./PageByUser.css";
import { toast } from "react-toastify";
import axios from "axios";
import TextField from "@mui/material/TextField";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router";

export default function PageByUser() {
  const theme = createTheme();
  const [userId, setUserId] = useState("");
  const navigate = useNavigate();

  const submit = () => {
    console.log(userId);
    axios
      .get("http://localhost:8080/users/migrateUser/" + userId, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
        toast.success(response.data);
        navigate("/");
      });
  };

  return (
    <div className="PagesByUser">
      <ThemeProvider theme={theme}>
        <div className="text-container">
          <Grid item xs={4}>
            <Card
              sx={{
                minWidth: 10 + "%",
                height: 250,
                backgroundColor: " rgb(190, 233, 219);",
              }}
            >
              <CardContent>
                <Typography
                  sx={{ fontSize: 22, textAlign: "center" }}
                  color="text.secondary"
                  gutterBottom
                >
                  Data migration using ID involves moving data from one system
                  to another while preserving unique identifiers, using them as
                  keys to match data between systems..A data migration service
                  can supplement your in-house capabilities or manage the entire
                  migration process from strategy through completion, testing,
                  and documentation. This data migration is GDPR(General Data
                  Protection Regulation ) compatible.
                </Typography>
              </CardContent>
              <CardActions></CardActions>
            </Card>
          </Grid>

          <div className="corosell">
            <div classsname="dropBtn" style={{ margin: 20, width: 400 }}>
              <TextField
                autoComplete="given-name"
                name="User ID"
                required
                fullWidth
                id="userId"
                value={userId}
                placeholder="Enter User ID"
                onChange={(event) => {
                  setUserId(event.target.value);
                }}
                variant="outlined"
              ></TextField>

              <Button>
                <input
                  type="submit"
                  id="submit-bt2"
                  value="Submit"
                  onClick={submit}
                />
              </Button>
            </div>
          </div>
        </div>
      </ThemeProvider>
    </div>
  );
}
