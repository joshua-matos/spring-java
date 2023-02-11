import React from "react";
import { screen } from "@testing-library/react";
// import { NoteSection } from "../NoteSection";

describe("NoteSection", () => {
  it("should render Create Note title", async () => {
    // renderQueryClient(<NoteSection />);
    expect(
      await screen.findByRole("button", { name: /Create Note/i })
    ).toBeInTheDocument();
  });
});
