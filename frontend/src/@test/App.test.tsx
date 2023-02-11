import React from "react";
import { screen } from "@testing-library/react";
import App from "../App";
import { renderQueryClient } from "./helper/RenderQueryClient";

describe("App renders NoteSection", function () {
  it("should render a an item from NoteSection", () => {
    renderQueryClient(<App />);
    const title = screen.getByRole("heading", { name: /the note taker/i });
    const createNoteButton = screen.getByText(/Create note/i);

    expect(title).toBeVisible();
    expect(createNoteButton).toBeInTheDocument();
  });
});
