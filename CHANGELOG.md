# v1.1.0 - Major Update
## Built-in Components
1. Added `Spacer` - Allows spacing out items in a Row/Column/ListView
2. Added `If` - Allows child component to be displayed if a condition is met

## Others
1. Added Color Schemes
2. Added parameter boolean `drawColor` to `Container`
3. Added color parameters to `Tooltip`
4. Created `StringAlignUtils`
5. Added option to not render gray background in ScreenUIRenderer
6. Added `sizeBehaviour` field to `Container`
7. Fixed `DynamicUIComponent` crashes

# v1.0.5 - Minor Update
## Built-in Components
1. Added `Slider`
2. Added `FlatButton`

## Others
1. Texture.Builder now has a constructor parameter for texture location instead of it being in the build method
2. `Button` now uses GuiUtils.drawContinuousTextureBox so it can be any size without any problems
3. All UIComponents that takes in a list of children now accept Iterables and Arrays.
4. Fixed tooltip being drawn over
5. Fixed `Padding` not behaving as intended

# v1.0.4 - Minor Update
## Others
1. `Button`'s `onPress` callback is now a `Consumer<Integer>` instead of `Runnable` and accepts an integer as the mouse button pressed
2. Fixed `Text`'s `WRAP` overflow not behaving as intended
3. Fixed `Text`'s `CLIP` overflow not behaving as intended
4. Added `ELLIPSIS` overflow behaviour to text
5. `Texture`'s default uvSize is now the texture size instead of 0x0
6. Added `size` field to Texture

# v1.0.3 - Minor Update
## Others
1. Renamed interface `Renderer` to `RenderProvider`
2. Created built-in `RenderProvider` - `ItemRenderProvider`

# v1.0.2 - Minor Update
## Built-in Components
1. Added `Tooltip` that renders a tooltip when child component is hovered

# v1.0.1 - Minor Update
## Built-in Components
1. Added `Texture` that renders a texture
2. Added `ListView` that displays a list of items in a columns and creates a scrollable view
3. Added `Clip` that clips all graphics drawn outside its boundary
4. Added `Renderable` that is just a wrapper for an IRenderable

## Others
1. Added `getPosition` overload in Layout that returns position in `Vector2i` and takes in the size with `Vector2i`
2. Fixed `Container` wasn't actually using the color given by the parameter
3. Added `within` static method in `Vector2i` that checks if a coordinate is within a `Vector2i` of postiion and another `Vector2i` of size
4. Fixed `DynamicUIComponent` not being setup correctly if it implements `PrimitiveUIComponent`
5. Fixed crashes with `DynamicUIComponent`
6. `DynamicUIComponent` now caches layout internally and no longer requires `Layout` to be passed into `rebuild`
7. Made small performance improvement to `DynamicUIComponent`
8. Added `copy` method to `Layout` and `StackedAxialSettings` that deep copies the data
9. Added new `Size` function
10. Added new overload for percentage `Size`
11. Added new overload for static `Size`

## Known Issues
1. IGuiEventListeners are interactable after being clipped
---

# v1.0.0 - Initial Release

## Built-in Renderers
1. ScreenUIRenderer
2. HudUIRenderer

## Built-in Components
1. Align
2. Button
3. Center
4. Column
5. Container
6. Padding
7. Positioned
8. Render
9. Row
10. Sized
11. Stack
12. Text
13. TextField
14. Transform
15. WidgetWrapper