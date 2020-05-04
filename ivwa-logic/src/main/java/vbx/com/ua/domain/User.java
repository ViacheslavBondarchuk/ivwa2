package vbx.com.ua.domain;

import java.util.Set;

public class User {
    private long id;
    private String username;
    private String password;
    private String email;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsConExpired;
    private Set<Authority> authorities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsConExpired() {
        return isCredentialsConExpired;
    }

    public void setCredentialsConExpired(boolean credentialsConExpired) {
        isCredentialsConExpired = credentialsConExpired;
    }

    public static Builder builder() {
        return new User().new Builder();
    }

    public User() {
    }

    public User(long id, String username, String password, String email, boolean isEnabled
            , boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsConExpired, Set<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsConExpired = isCredentialsConExpired;
        this.authorities = authorities;
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(long id) {
            User.this.setId(id);
            return this;
        }

        public Builder setUsername(String username) {
            User.this.setUsername(username);
            return this;
        }

        public Builder setPassword(String password) {
            User.this.setPassword(password);
            return this;
        }

        public Builder setEmail(String email) {
            User.this.setEmail(email);
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            User.this.setEnabled(enabled);
            return this;
        }

        public Builder setAccountNonLocked(boolean accountNonLocked) {
            User.this.setAccountNonLocked(accountNonLocked);
            return this;
        }

        public Builder setAccountNonExpired(boolean accountNonExpired) {
            User.this.setAccountNonExpired(accountNonExpired);
            return this;
        }

        public Builder setCredentialsConExpired(boolean credentialsConExpired) {
            User.this.setCredentialsConExpired(credentialsConExpired);
            return this;
        }

        public Builder setAuthorities(Set<Authority> authorities) {
            User.this.setAuthorities(authorities);
            return this;
        }

        public User build() {
            return User.this;
        }

    }

}
